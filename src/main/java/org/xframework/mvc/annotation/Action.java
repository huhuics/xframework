/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action注解定义
 * @author HuHui
 * @version $Id: Action.java, v 0.1 2017年4月19日 下午5:09:25 HuHui Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

}
