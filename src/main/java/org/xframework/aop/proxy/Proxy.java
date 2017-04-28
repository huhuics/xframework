/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop.proxy;

/**
 * 代理接口
 * @author HuHui
 * @version $Id: Proxy.java, v 0.1 2017年4月28日 上午11:06:52 HuHui Exp $
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain  代理链
     * @return
     * @throws Throwable  异常
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
