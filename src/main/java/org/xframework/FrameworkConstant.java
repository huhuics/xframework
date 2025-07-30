/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework;

import org.xframework.core.ConfigHelper;

/**
 * 常量定义
 * @author HuHui
 * @version $Id: FrameworkConstant.java, v 0.1 2017年4月7日 上午11:28:28 HuHui Exp $
 */
public interface FrameworkConstant {

    String UTF_8          = "UTF-8";

    String CONFIG_PROPS   = "xframe.properties";

    String SQL_PROPS      = "xframe-sql.properties";

    String PLUGIN_PACKAGE = "org.xframework.plugin";

    String JSP_PATH       = ConfigHelper.getString("xframe.app.jsp_path", "/WEB-INF/jsp/");

    String WWW_PATH       = ConfigHelper.getString("xframe.app.www_path", "/www/");

    String HOME_PAGE      = ConfigHelper.getString("xframe.app.home_page", "/index.jsp");

    int    UPLOAD_LIMIT   = ConfigHelper.getInt("xframe.app.upload_limit", 10);

    String PK_NAME        = "id";

}
