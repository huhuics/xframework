/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import java.lang.reflect.Method;

import org.xframework.aop.AspectProxy;

/**
 * 
 * @author HuHui
 * @version $Id: AfterProxy.java, v 0.1 2017年5月5日 上午10:58:58 HuHui Exp $
 */
public class AfterProxy extends AspectProxy {

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        System.out.println("After");
    }

}
