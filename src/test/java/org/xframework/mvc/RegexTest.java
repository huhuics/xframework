/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.xframework.BaseTest;
import org.xframework.util.LogUtil;

/**
 * 测试URL正则
 * @author HuHui
 * @version $Id: RegexTest.java, v 0.1 2017年4月25日 下午7:48:47 HuHui Exp $
 */
public class RegexTest extends BaseTest {

    @Test
    public void testMatcher() {
        String regex = "\\w+";
        String input = "a12da";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        LogUtil.info(logger, "group count={0}", matcher.groupCount());
        if (matcher.matches()) {
            LogUtil.info(logger, "匹配成功");
        } else {
            LogUtil.info(logger, "匹配失败");
        }

        for (int i = 0; i <= matcher.groupCount(); i++) {
            String group = matcher.group(i);
            LogUtil.info(logger, "group={0}", group);
        }
    }
}
