/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.util;

import org.junit.Test;
import org.xframework.BaseTest;

/**
 * 
 * @author HuHui
 * @version $Id: HashCodeTest.java, v 0.1 2017年4月30日 上午10:04:46 HuHui Exp $
 */
public class HashCodeTest extends BaseTest {

    @Test
    public void testHashCode() {
        String a = "abc";
        String b = "abc";
        LogUtil.info(logger, "a.hashCode={0}, b.hashCode={1}", a.hashCode(), b.hashCode());
    }

    class Ab {
    }

    class Ac {
    }

}
