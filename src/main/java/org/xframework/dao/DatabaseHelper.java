/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.xframework.InstanceFactory;
import org.xframework.ds.DataSourceFactory;

/**
 * 封装数据库相关操作
 * @author HuHui
 * @version $Id: DatabaseHelper.java, v 0.1 2017年5月1日 下午2:00:48 HuHui Exp $
 */
public class DatabaseHelper {

    /**
     * 定义一个局部线程变量(使每个线程都拥有自己的连接)
     */
    private static final ThreadLocal<Connection> connContainer     = new ThreadLocal<Connection>();

    /**
     * 获取数据源工厂
     */
    private static final DataSourceFactory       dataSourceFactory = InstanceFactory.getDataSourceFactory();

    /**
     * 获取数据访问器
     */
    private static final DataAccessor            dataAccessor      = InstanceFactory.getDataAccessor();

    /**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return dataSourceFactory.getDataSource();
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn;
        try {
            //先从ThreadLocal中获取Connection
            conn = connContainer.get();
            if (conn == null) {
                //如果不存在则从DataSource中获取Connection
                conn = getDataSource().getConnection();
                //将Connection放入ThreadLocal中
                if (conn != null) {
                    connContainer.set(conn);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接出错", e);
        }
        return conn;
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException("开启事务出错", e);
            } finally {
                connContainer.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("提交事务出错", e);
            } finally {
                connContainer.remove();
            }

        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("回滚事务出错", e);
            } finally {
                connContainer.remove();
            }
        }
    }

    /**
     * 根据 SQL 语句查询 Entity
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntity(entityClass, sql, params);
    }

    /**
     * 根据 SQL 语句查询 Entity 列表
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntityList(entityClass, sql, params);
    }

    /**
     * 根据 SQL 语句查询 Entity 映射（Field Name => Field Value）
     */
    public static <K, V> Map<K, V> queryEntityMap(Class<V> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntityMap(entityClass, sql, params);
    }

    /**
     * 根据 SQL 语句查询 Array 格式的字段（单条记录）
     */
    public static Object[] queryArray(String sql, Object... params) {
        return dataAccessor.queryArray(sql, params);
    }

    /**
     * 根据 SQL 语句查询 Array 格式的字段列表（多条记录）
     */
    public static List<Object[]> queryArrayList(String sql, Object... params) {
        return dataAccessor.queryArrayList(sql, params);
    }

    /**
     * 根据 SQL 语句查询 Map 格式的字段（单条记录）
     */
    public static Map<String, Object> queryMap(String sql, Object... params) {
        return dataAccessor.queryMap(sql, params);
    }

    /**
     * 根据 SQL 语句查询 Map 格式的字段列表（多条记录）
     */
    public static List<Map<String, Object>> queryMapList(String sql, Object... params) {
        return dataAccessor.queryMapList(sql, params);
    }

    /**
     * 根据 SQL 语句查询指定字段（单条记录）
     */
    public static <T> T queryColumn(String sql, Object... params) {
        return dataAccessor.queryColumn(sql, params);
    }

    /**
     * 根据 SQL 语句查询指定字段列表（多条记录）
     */
    public static <T> List<T> queryColumnList(String sql, Object... params) {
        return dataAccessor.queryColumnList(sql, params);
    }

    /**
     * 根据 SQL 语句查询指定字段映射（多条记录）
     */
    public static <T> Map<T, Map<String, Object>> queryColumnMap(String column, String sql, Object... params) {
        return dataAccessor.queryColumnMap(column, sql, params);
    }

    /**
     * 根据 SQL 语句查询记录条数
     */
    public static long queryCount(String sql, Object... params) {
        return dataAccessor.queryCount(sql, params);
    }

    /**
     * 执行更新语句（包括：update、insert、delete）
     */
    public static int update(String sql, Object... params) {
        return dataAccessor.update(sql, params);
    }

    /**
     * 执行插入语句，返回插入后的主键
     */
    public static Serializable insertReturnPK(String sql, Object... params) {
        return dataAccessor.insertReturnPK(sql, params);
    }

}
