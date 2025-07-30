/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.tx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Service注解
 * @author HuHui
 * @version $Id: Service.java, v 0.1 2017年4月19日 下午5:49:14 HuHui Exp $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

}
