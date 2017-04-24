/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 类扫描器
 * @author HuHui
 * @version $Id: ClassScanner.java, v 0.1 2017年4月7日 下午8:53:06 HuHui Exp $
 */
public interface ClassScanner {

    /**
     * 获取指定包名中的所有类
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名中指定注解的相关类
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口的相关类
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);

}
