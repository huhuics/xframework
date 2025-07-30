/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.aop;

/**
 * 
 * @author HuHui
 * @version $Id: Client.java, v 0.1 2017年5月4日 下午4:46:30 HuHui Exp $
 */
public class Client {

    public static void main(String[] args) {
        Object beforeProxy = new BeforeProxy().getProxy(Greeting.class);
        Object afterProxy = new AfterProxy().getProxy(Greeting.class);
        Greeting greeting = (Greeting) beforeProxy;
        greeting.sayHello("CGLib");

        greeting = (Greeting) afterProxy;
        greeting.sayHello("CGLib");
    }

}
