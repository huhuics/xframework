/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理管理器
 * @author HuHui
 * @version $Id: ProxyManager.java, v 0.1 2017年4月28日 上午11:22:36 HuHui Exp $
 */
public class ProxyManager {

    /**
     * 创建代理类
     * @param targetClass  目标类
     * @param proxyList    对目标类的增强类集合
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList).doProxyChain();
            }
        });
    }

}
