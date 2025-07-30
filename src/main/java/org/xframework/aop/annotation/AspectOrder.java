/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义切面顺序
 * @author HuHui
 * @version $Id: AspectOrder.java, v 0.1 2017年4月28日 下午4:31:54 HuHui Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectOrder {

    int value();

}
