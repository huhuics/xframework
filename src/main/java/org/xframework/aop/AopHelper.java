/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.xframework.FrameworkConstant;
import org.xframework.InstanceFactory;
import org.xframework.aop.annotation.Aspect;
import org.xframework.aop.annotation.AspectOrder;
import org.xframework.aop.proxy.Proxy;
import org.xframework.aop.proxy.ProxyManager;
import org.xframework.core.ClassHelper;
import org.xframework.core.ClassScanner;
import org.xframework.ioc.BeanHelper;
import org.xframework.tx.TransactionProxy;
import org.xframework.tx.annotation.Service;
import org.xframework.util.ClassUtil;

/**
 * 初始化AOP框架
 * @author HuHui
 * @version $Id: AopHelper.java, v 0.1 2017年4月28日 下午4:06:11 HuHui Exp $
 */
public class AopHelper {

    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    static {
        try {
            //创建Proxy Map (用于存放代理类与目标类列表的映射关系,例如class org.debug.aspect.ActionAspect=[class org.debug.action.AccountAction, class org.debug.action.UserAction])
            Map<Class<?>, List<Class<?>>> proxyMap = createProxyMap();
            //创建Target Map (用于存放目标类与代理类列表的映射关系,例如class org.debug.action.AccountAction=[org.debug.aspect.ActionAspect@71a40dde], class org.debug.action.UserAction=[org.debug.aspect.ActionAspect@33bfe151])
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            //遍历Target Map
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                //分别获取map中key与value
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                //创建代理实例
                Object proxyInstance = ProxyManager.createProxy(targetClass, proxyList);
                //用代理实例覆盖目标实例,并放入Bean容器中
                BeanHelper.setBean(targetClass, proxyInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化AopHelper出错", e);
        }
    }

    private static Map<Class<?>, List<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, List<Class<?>>> proxyMap = new LinkedHashMap<Class<?>, List<Class<?>>>();
        //添加相关代理
        addPluginProxy(proxyMap); //插件代理
        addAspectProxy(proxyMap); //切面代理
        addTransactionProxy(proxyMap); //事务代理
        return proxyMap;
    }

    private static void addPluginProxy(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        //TODO:获取插件包中父类为PluginProxy的所有类(插件代理类)
    }

    private static void addAspectProxy(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        //获取切面类(所有继承于AspectProxy的类)
        List<Class<?>> aspectProxyClassList = ClassHelper.getClassListBySuper(AspectProxy.class);
        //添加插件包下所有的切面类
        aspectProxyClassList.addAll(classScanner.getClassListBySuper(FrameworkConstant.PLUGIN_PACKAGE, AspectProxy.class));
        //排序切面类
        sortAspectProxyClassList(aspectProxyClassList);
        //遍历切面类
        for (Class<?> aspectProxyClass : aspectProxyClassList) {
            //判断Aspect注解是否存在
            if (aspectProxyClass.isAnnotationPresent(Aspect.class)) {
                //获取Aspect注解
                Aspect aspect = aspectProxyClass.getAnnotation(Aspect.class);
                //创建目标类列表
                List<Class<?>> targetClassList = createTargetClassList(aspect);
                //初始化Proxy Map
                proxyMap.put(aspectProxyClass, targetClassList);
            }
        }

    }

    private static void addTransactionProxy(Map<Class<?>, List<Class<?>>> proxyMap) {
        //使用TransactionProxy代理所有Service类
        List<Class<?>> serviceClassList = ClassHelper.getClassListByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, serviceClassList);
    }

    private static void sortAspectProxyClassList(List<Class<?>> proxyClassList) {
        //排序代理类列表
        Collections.sort(proxyClassList, new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> aspect1, Class<?> aspect2) {
                if (aspect1.isAnnotationPresent(AspectOrder.class) || aspect2.isAnnotationPresent(AspectOrder.class)) {
                    //如果有AspectOrder注解,则优先比较(序号值越小越靠前)
                    if (aspect1.isAnnotationPresent(AspectOrder.class)) {
                        return getOrderValue(aspect1) - getOrderValue(aspect2);
                    } else {
                        return getOrderValue(aspect2) - getOrderValue(aspect1);
                    }
                } else {
                    //若无AspectOrder注解,则比较类名(按字母顺序升序排列)
                    return aspect1.hashCode() - aspect2.hashCode();
                }
            }

            private int getOrderValue(Class<?> aspect) {
                return aspect.getAnnotation(AspectOrder.class) != null ? aspect.getAnnotation(AspectOrder.class).value() : 0;
            }

        });
    }

    private static List<Class<?>> createTargetClassList(Aspect aspect) throws Exception {
        List<Class<?>> targetClassList = new ArrayList<Class<?>>();
        //获取Aspect注解的相关属性
        String pkg = aspect.pkg();
        String cls = aspect.cls();
        Class<? extends Annotation> annotation = aspect.annotation();
        //若包名不为空,则需进一步判断类名是否为空
        if (StringUtils.isNotEmpty(pkg)) {
            if (StringUtils.isNotEmpty(cls)) {
                //若类名不为空,则仅添加该类
                targetClassList.add(ClassUtil.loadClass(pkg + "." + cls, false));
            } else {
                //若注解不为空且不是Aspect注解,则添加指定包名下带有该注解的所有类
                if (annotation != null && !annotation.equals(Aspect.class)) {
                    targetClassList.addAll(classScanner.getClassListByAnnotation(pkg, annotation));
                } else {
                    //否则添加该包名下所有类
                    targetClassList.addAll(classScanner.getClassList(pkg));
                }
            }
        } else {
            //若注解不为空且不是Aspect注解,则添加应用包名下带有该注解的所有类
            if (annotation != null && !annotation.equals(Aspect.class)) {
                targetClassList.addAll(ClassHelper.getClassListByAnnotation(annotation));
            }
        }
        return targetClassList;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        //遍历Proxy Map
        for (Map.Entry<Class<?>, List<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            //分别获取map中的key和value
            Class<?> proxyClass = proxyEntry.getKey();
            List<Class<?>> targetClassList = proxyEntry.getValue();
            //遍历目标类列表
            for (Class<?> targetClass : targetClassList) {
                //创建代理类(切面类)实例
                Proxy baseAspect = (Proxy) proxyClass.newInstance();
                //初始化Target Map
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(baseAspect);
                } else {
                    List<Proxy> baseAspectList = new ArrayList<Proxy>();
                    baseAspectList.add(baseAspect);
                    targetMap.put(targetClass, baseAspectList);
                }
            }
        }
        return targetMap;
    }
}
