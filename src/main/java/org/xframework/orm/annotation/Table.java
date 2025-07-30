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
 * 定义表名
 * @author HuHui
 * @version $Id: Table.java, v 0.1 2017年5月1日 下午4:23:51 HuHui Exp $
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String value();
}
