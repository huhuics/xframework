/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.xframe.test.BeanTest;

/**
 * 测试BeanHelper
 * @author HuHui
 * @version $Id: BeanHelperTest.java, v 0.1 2017年4月16日 下午1:59:14 HuHui Exp $
 */
public class BeanHelperTest {

    @Test
    public void testGetBean() {
        BeanHelper beanHelper = new BeanHelper();
        BeanTest bean = beanHelper.getBean(BeanTest.class);
        Assert.assertNotNull(bean);
    }

}
