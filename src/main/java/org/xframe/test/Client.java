/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import java.util.ArrayList;
import java.util.List;

import org.xframework.aop.proxy.Proxy;
import org.xframework.aop.proxy.ProxyManager;

/**
 * 
 * @author HuHui
 * @version $Id: Client.java, v 0.1 2017年5月5日 上午10:59:27 HuHui Exp $
 */
public class Client {

    public static void main(String[] args) {
        List<Proxy> proxyList = new ArrayList<Proxy>();
        proxyList.add(new BeforeProxy());
        proxyList.add(new AfterProxy());

        Greeting greetingProxy = ProxyManager.createProxy(Greeting.class, proxyList);
        greetingProxy.sayHello("Xframework");
    }
}
