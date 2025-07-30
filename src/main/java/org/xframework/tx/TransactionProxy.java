/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.tx;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xframework.aop.proxy.Proxy;
import org.xframework.aop.proxy.ProxyChain;
import org.xframework.dao.DatabaseHelper;
import org.xframework.tx.annotation.Transaction;
import org.xframework.util.LogUtil;

/**
 * 事务代理
 * @author HuHui
 * @version $Id: TransactionProxy.java, v 0.1 2017年4月30日 上午10:41:01 HuHui Exp $
 */
public class TransactionProxy implements Proxy {

    private static final Logger               logger        = LoggerFactory.getLogger(TransactionProxy.class);

    /**
     * 定义一个线程局部变量,用于保存当前线程中是否进行了事务处理,默认为false(未处理)
     */
    private static final ThreadLocal<Boolean> flagContainer = new ThreadLocal<Boolean>() {
                                                                @Override
                                                                protected Boolean initialValue() {
                                                                    return false;
                                                                }
                                                            };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        //判断当前线程是否进行了事务处理
        boolean flag = flagContainer.get();
        //获取目标方法
        Method method = proxyChain.getTargetMethod();
        //若当前线程未进行事务处理,且在目标方法上定义了Transaction注解,则说明该方法需要进行事务处理
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            //设置当前线程已进行事务处理
            flagContainer.set(true);
            try {
                //开启事务
                DatabaseHelper.beginTransaction();
                LogUtil.info(logger, "begin transaction");
                //执行目标方法
                result = proxyChain.doProxyChain();
                //提交事务
                DatabaseHelper.commitTransaction();
                LogUtil.info(logger, "commit transaction");
            } catch (Exception e) {
                DatabaseHelper.rollbackTransaction();
                LogUtil.info(logger, "rollback transaction");
                throw e;
            } finally {
                //移除线程局部变量
                flagContainer.remove();
            }
        } else {
            //执行目标方法
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
