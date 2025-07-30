/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import org.xframework.ioc.annotation.Bean;

/**
 * 
 * @author HuHui
 * @version $Id: UserBeanImpl.java, v 0.1 2017年4月18日 下午4:24:55 HuHui Exp $
 */
@Bean
public class UserBeanImpl implements UserBean {

    @Override
    public void display() {
        System.out.println("from UserBeanImpl");
    }

}
