/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.xframework.FrameworkConstant;
import org.xframework.mvc.UploadHelper;
import org.xframework.mvc.ViewResolver;
import org.xframework.mvc.bean.Result;
import org.xframework.mvc.bean.View;
import org.xframework.util.WebUtil;

/**
 * 默认视图解析器
 * @author HuHui
 * @version $Id: DefaultViewResolver.java, v 0.1 2017年4月20日 下午9:17:35 HuHui Exp $
 */
public class DefaultViewResolver implements ViewResolver {

    @Override
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult) {
        if (actionMethodResult != null) {
            //Action返回值可能为View或Result
            if (actionMethodResult instanceof View) {
                //若为View,则需要考虑两种视图类型——重定向或转发
                View view = (View) actionMethodResult;
                if (view.isRedirect()) {
                    //获取路径
                    String path = view.getPath();
                    //重定向请求
                    WebUtil.redirectRequest(path, request, response);
                } else { //转发
                    //获取路径
                    String path = FrameworkConstant.JSP_PATH + view.getPath();
                    //初始化请求属性
                    Map<String, Object> data = view.getData();
                    if (MapUtils.isNotEmpty(data)) {
                        for (Map.Entry<String, Object> entry : data.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                    }
                    //转发请求
                    WebUtil.forwardRequest(path, request, response);
                }
            } else {
                //若为Result,则需要考虑两种请求类型——文件上传或普通请求
                Result result = (Result) actionMethodResult;
                if (UploadHelper.isMultipart(request)) {
                    //对于multipart类型,说明是文件上传,需要转换为HTML格式并写入响应
                    WebUtil.writeHTML(response, result);
                } else {
                    //对于其它类型,统一转换为JSON格式并写入响应
                    WebUtil.writeJSON(response, result);
                }
            }
        }
    }

}
