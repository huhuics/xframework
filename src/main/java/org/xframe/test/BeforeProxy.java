/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import java.lang.reflect.Method;

import org.xframework.aop.AspectProxy;

/**
 * 
 * @author HuHui
 * @version $Id: BeforeProxy.java, v 0.1 2017年5月5日 上午10:58:08 HuHui Exp $
 */
public class BeforeProxy extends AspectProxy {

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("Before");
    }

}
