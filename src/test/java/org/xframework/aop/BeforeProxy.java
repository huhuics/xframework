/**
 *
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
 * @version $Id: BeforeProxy.java, v 0.1 2017年5月4日 下午4:39:40 HuHui Exp $
 */
public class BeforeProxy implements MethodInterceptor {

    public Object getProxy(Class<?> cls) {
        return Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        return proxy.invokeSuper(target, args);
    }

    private void before() {
        System.out.println("Before");
    }

}
