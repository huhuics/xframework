/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author HuHui
 * @version $Id: AfterProxy.java, v 0.1 2017年5月4日 下午4:43:38 HuHui Exp $
 */
public class AfterProxy implements MethodInterceptor {

    public Object getProxy(Class<?> cls) {
        return Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        after();
        return proxy.invokeSuper(target, args);
    }

    private void after() {
        System.out.println("After");
    }

}
