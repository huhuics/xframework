/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author HuHui
 * @version $Id: DynamicProxy.java, v 0.1 2017年4月26日 下午8:26:09 HuHui Exp $
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(target, args);
        System.out.println("after");
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), interfaces, this);
    }

}
