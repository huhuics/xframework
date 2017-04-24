/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework;

import org.xframework.ioc.BeanHelper;
import org.xframework.ioc.IocHelper;
import org.xframework.mvc.ActionHelper;
import org.xframework.util.ClassUtil;

/**
 * 加载一些Helper类
 * @author HuHui
 * @version $Id: HelperLoader.java, v 0.1 2017年4月20日 下午4:35:15 HuHui Exp $
 */
public class HelperLoader {

    public static void init() {
        Class<?>[] classList = { //定义需要加载的Helper类
        ActionHelper.class, //
                BeanHelper.class, //
                IocHelper.class // 
        };

        //加载类
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }

}
