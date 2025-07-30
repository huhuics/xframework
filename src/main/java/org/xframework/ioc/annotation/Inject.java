/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 依赖注入注解
 * @author HuHui
 * @version $Id: Inject.java, v 0.1 2017年4月11日 下午5:24:14 HuHui Exp $
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

}
