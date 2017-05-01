/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.ds.impl;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 
 * @author HuHui
 * @version $Id: DefaultDataSourceFactory.java, v 0.1 2017年5月1日 下午2:31:10 HuHui Exp $
 */
public class DefaultDataSourceFactory extends AbstractDataSourceFactory<BasicDataSource> {

    @Override
    public BasicDataSource createDataSource() {
        return new BasicDataSource();
    }

    @Override
    public void setDriver(BasicDataSource ds, String driver) {
        ds.setDriverClassName(driver);
    }

    @Override
    public void setUrl(BasicDataSource ds, String url) {
        ds.setUrl(url);
    }

    @Override
    public void setUsername(BasicDataSource ds, String username) {
        ds.setUsername(username);
    }

    @Override
    public void setPassword(BasicDataSource ds, String password) {
        ds.setPassword(password);
    }

    @Override
    public void setAdvancedConfig(BasicDataSource ds) {
        //解决java.sql.SQLException: Already closed. 问题(连接池会自动关闭长时间没有使用的连接)
        ds.setValidationQuery("select 1 from dual");
    }

}
