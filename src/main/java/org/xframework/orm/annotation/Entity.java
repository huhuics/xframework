/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义Entity类的注解
 * @author HuHui
 * @version $Id: Entity.java, v 0.1 2017年5月1日 下午4:20:48 HuHui Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
}
