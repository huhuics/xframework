/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframe.test;

import org.xframework.ioc.annotation.Bean;
import org.xframework.ioc.annotation.Inject;

/**
 * 
 * @author HuHui
 * @version $Id: BeanTest.java, v 0.1 2017年4月16日 下午2:07:55 HuHui Exp $
 */
@Bean
public class BeanTest {

    @Inject
    private UserBean userBean;

    public void say() {
        System.out.println("32");
    }

}
