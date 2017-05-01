/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xframework.dao.DataAccessor;
import org.xframework.dao.DatabaseHelper;
import org.xframework.util.LogUtil;

/**
 * 默认数据访问器, 基于Apache Commons DbUtils实现
 * @author HuHui
 * @version $Id: DefaultDataAccessor.java, v 0.1 2017年5月1日 下午3:03:46 HuHui Exp $
 */
public class DefaultDataAccessor implements DataAccessor {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataAccessor.class);

    private final QueryRunner   queryRunner;

    public DefaultDataAccessor() {
        DataSource dataSource = DatabaseHelper.getDataSource();
        queryRunner = new QueryRunner(dataSource);
    }

    @Override
    public <T> T queryEntiry(Class<T> entityClass, String sql, Object... params) {
        return null;
    }

    @Override
    public <T> List<T> queryEntityList(Class<T> entiryClass, String sql, Object... params) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> queryEntityMap(Class<V> entityClass, String sql, Object... params) {
        return null;
    }

    @Override
    public Object[] queryArray(String sql, Object... params) {
        return null;
    }

    @Override
    public List<Object[]> queryArrayList(String sql, Object... params) {
        return null;
    }

    @Override
    public Map<String, Object> queryMap(String sql, Object... params) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryMapList(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> T queryColumn(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> List<T> queryColumnList(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> Map<T, Map<String, Object>> queryColumnMap(String column, String sql, Object... params) {
        return null;
    }

    @Override
    public long queryCount(String sql, Object... params) {
        return 0;
    }

    @Override
    public int update(String sql, Object... params) {
        return 0;
    }

    @Override
    public Serializable insertReturnPK(String sql, Object... params) {
        return null;
    }

    private static void printSQL(String sql) {
        LogUtil.debug(logger, "[xframework] SQL - {0}", sql);
    }

}
