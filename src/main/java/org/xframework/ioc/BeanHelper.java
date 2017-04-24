/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.ioc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xframework.aop.annotation.Aspect;
import org.xframework.core.ClassHelper;
import org.xframework.ioc.annotation.Bean;
import org.xframework.mvc.annotation.Action;
import org.xframework.tx.annotation.Service;

/**
 * 初始化被自定义注解标注的类
 * @author HuHui
 * @version $Id: BeanHelper.java, v 0.1 2017年4月16日 上午11:26:02 HuHui Exp $
 */
public class BeanHelper {

    /** key为Bean类, 值为Bean实例 */
    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            //获取应用包路径下所有的类
            List<Class<?>> classList = ClassHelper.getClassList();
            for (Class<?> cls : classList) {
                if (cls.isAnnotationPresent(Bean.class) //Bean
                    || cls.isAnnotationPresent(Service.class) //Impl
                    || cls.isAnnotationPresent(Action.class) //Action
                    || cls.isAnnotationPresent(Aspect.class)) { //Aspect
                    //创建Bean实例
                    Object beanInstance = cls.newInstance();
                    //将Bean实例放入Map中
                    beanMap.put(cls, beanInstance);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化Bean出错", e);
        }
    }

    /**
     * 获取Bean Map
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * 获取Bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("无法根据类名获取实例");
        }
        return (T) beanMap.get(cls);
    }

    public static void setBean(Class<?> cls, Object obj) {
        beanMap.put(cls, obj);
    }

}
