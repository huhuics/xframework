/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import java.lang.reflect.Proxy;

/**
 * 
 * @author HuHui
 * @version $Id: OperateImpl.java, v 0.1 2017年4月26日 下午8:25:16 HuHui Exp $
 */
public class OperateImpl implements Operate {

    /** 
     * @see org.xframe.test.Operate#say()
     */
    @Override
    public void say() {
        System.out.println("function from OperateImpl");
    }

    public static void main(String[] args) {
        Operate operate = new OperateImpl();
        Operate proxyOperate = (Operate) Proxy.newProxyInstance(operate.getClass().getClassLoader(), operate.getClass().getInterfaces(), new DynamicProxy(
            operate));
        proxyOperate.say();

        DynamicProxy dp = new DynamicProxy(new OperateImpl());
        Operate proxy = dp.getProxy();
        proxy.say();
    }
}
