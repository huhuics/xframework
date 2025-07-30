/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 
 * @author HuHui
 * @version $Id: CGLibProxy.java, v 0.1 2017年4月26日 下午8:50:10 HuHui Exp $
 */
public class CGLibProxy implements MethodInterceptor {

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object object, Method method, Object[] objArray, MethodProxy proxy) throws Throwable {
        System.out.println("before");
        Object result = proxy.invokeSuper(object, objArray);
        System.out.println("after");
        return result;
    }

    public static void main(String[] args) {
        CGLibProxy proxy = new CGLibProxy();
        BeanTest bt = proxy.getProxy(BeanTest.class);
        bt.say();
    }
}
