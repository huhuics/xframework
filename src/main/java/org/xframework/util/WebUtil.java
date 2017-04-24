/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.xframework.FrameworkConstant;

import com.alibaba.fastjson.JSON;

/**
 * Web操作工具类
 * @author HuHui
 * @version $Id: WebUtil.java, v 0.1 2017年4月21日 上午11:05:38 HuHui Exp $
 */
public class WebUtil {

    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

    /**
     * 将数据以JSON格式写入响应中
     */
    public static void writeJSON(HttpServletResponse response, Object data) {
        try {
            //设置响应头
            response.setContentType("application/json");
            response.setCharacterEncoding(FrameworkConstant.UTF_8);
            //在响应中写数据
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(data));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("以JSON格式写入响应失败", e);
        }
    }

    /**
     * 将数据以HTML格式写入响应中
     */
    public static void writeHTML(HttpServletResponse response, Object data) {
        try {
            //设置响应头
            response.setContentType("text/html");
            response.setCharacterEncoding(FrameworkConstant.UTF_8);
            //在响应中写数据
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(data));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("以HTML格式写入响应失败", e);
        }
    }

    /**
     * 从请求中获取所有参数(当参数名重复时,用后者覆盖前者)
     */
    public static Map<String, Object> getRequestParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        try {
            String method = request.getMethod();
            if (method.equalsIgnoreCase("put") || method.equalsIgnoreCase("delete")) {
                //TODO 处理put和delete请求
            } else {
                Enumeration<String> paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements()) {
                    String paramName = paramNames.nextElement();
                    if (checkParamName(paramName)) {
                        String[] paramValues = request.getParameterValues(paramName);
                        if (ArrayUtils.isNotEmpty(paramValues)) {
                            if (paramValues.length == 1) {
                                paramMap.put(paramName, paramValues[0]);
                            } else {
                                StringBuilder paramValue = new StringBuilder("");
                                for (int i = 0; i < paramValues.length; i++) {
                                    paramValue.append(paramValues[i]);
                                    if (i != paramValues.length - 1) {
                                        paramValue.append(SEPARATOR);
                                    }
                                }
                                paramMap.put(paramName, paramValue.toString());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("获取HttpServletRequest请求参数出错", e);
        }
        return paramMap;
    }

    private static boolean checkParamName(String paramName) {
        return !paramName.equals("_");//忽略jQuery缓存参数
    }

    /**
     * 判断是否为AJAX请求
     */
    public static boolean isAJAX(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null;
    }

    /**
     * 获取请求路径
     */
    public static String getRequestPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = StringUtils.defaultIfEmpty(request.getPathInfo(), "");
        return servletPath + pathInfo;
    }

    /**
     * 发送错误代码
     */
    public static void sendError(int code, String message, HttpServletResponse response) {
        try {
            response.sendError(code, message);
        } catch (IOException e) {
            throw new RuntimeException("发送错误代码出错", e);
        }
    }

    /**
     * 转发请求
     */
    public static void forwardRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("转发HTTP请求出错", e);
        }
    }

    /**
     * 重定向请求
     */
    public static void redirectRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (IOException e) {
            throw new RuntimeException("重定向HTTP请求出错", e);
        }
    }

}
