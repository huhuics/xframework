/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切面注解
 * @author HuHui
 * @version $Id: Aspect.java, v 0.1 2017年4月19日 下午5:51:46 HuHui Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 包名
     */
    String pkg() default "";

    /**
     * 类名
     */
    String cls() default "";

    /**
     * 注解
     */
    Class<? extends Annotation> annotation() default Aspect.class;

}
