/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop;

import java.util.List;
import java.util.Map;

import org.xframework.FrameworkConstant;
import org.xframework.InstanceFactory;
import org.xframework.core.ClassHelper;
import org.xframework.core.ClassScanner;

/**
 * 初始化AOP框架
 * @author HuHui
 * @version $Id: AopHelper.java, v 0.1 2017年4月28日 下午4:06:11 HuHui Exp $
 */
public class AopHelper {

    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    private static void addPluginProxy(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        //TODO:获取插件包中父类为PluginProxy的所有类(插件代理类)
    }

    private static void addAspectProxy(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        //获取切面类(所有继承于AspectProxy的类)
        List<Class<?>> aspectProxyClassList = ClassHelper.getClassListBySuper(AspectProxy.class);
        //添加插件包下所有的切面类
        aspectProxyClassList.addAll(classScanner.getClassListBySuper(FrameworkConstant.PLUGIN_PACKAGE, AspectProxy.class));
        //排序切面类

    }

    private static void sortAspectProxyClassList(List<Class<?>> proxyClassList) {

    }

}
