/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.xframework.BaseTest;
import org.xframework.util.LogUtil;

/**
 * 测试ConfigHelper
 * @author HuHui
 * @version $Id: ConfigHelperTest.java, v 0.1 2017年4月7日 下午3:26:39 HuHui Exp $
 */
public class ConfigHelperTest extends BaseTest {

    @Test
    public void testGetString() {
        String value1 = ConfigHelper.getString("xframe.app.home_page");
        LogUtil.info(logger, "value1={0}", value1);
        Assert.assertTrue(StringUtils.equals(value1, "/users2"));
    }
}
