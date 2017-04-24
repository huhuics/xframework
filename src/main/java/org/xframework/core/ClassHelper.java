/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core;

import java.lang.annotation.Annotation;
import java.util.List;

import org.xframework.InstanceFactory;

/**
 * 根据条件获取相关类
 * @author HuHui
 * @version $Id: ClassHelper.java, v 0.1 2017年4月7日 下午8:50:04 HuHui Exp $
 */
public class ClassHelper {

    /** 获取基础包名 */
    private static final String       basePackage  = ConfigHelper.getString("xframe.app.base_package");

    /** 获取ClassScanner */
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    /**
     * 获取基础包名中的所有类
     */
    public static List<Class<?>> getClassList() {
        return classScanner.getClassList(basePackage);
    }

    /**
     * 获取基础包名中指定父类或接口的相关类
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(basePackage, superClass);
    }

    /**
     * 获取基础包名中指定注解的相关类
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassListByAnnotation(basePackage, annotationClass);
    }

}
