/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core.impl.support;

/**
 * 
 * @author HuHui
 * @version $Id: SupperClassTemplate.java, v 0.1 2017年4月8日 上午8:58:00 HuHui Exp $
 */
public abstract class SuperClassTemplate extends ClassTemplate {

    protected final Class<?> superClass;

    protected SuperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }

}
