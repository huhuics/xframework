/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xframework.FrameworkConstant;

/**
 * 封装文件上传相关操作
 * @author HuHui
 * @version $Id: UploadHelper.java, v 0.1 2017年4月21日 上午11:26:56 HuHui Exp $
 */
public class UploadHelper {

    /**
     * FileUpload对象,用于解析所上传的文件
     */
    private static ServletFileUpload fileUpload;

    /**
     * 初始化
     */
    public static void init(ServletContext servletContext) {
        //获取一个临时目录(使用tomcat的work目录)
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //创建fileUpload对象
        fileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
        //设置上传限制
        int uploadLimit = FrameworkConstant.UPLOAD_LIMIT;
        if (uploadLimit > 0) {
            fileUpload.setFileSizeMax(uploadLimit * 1024 * 1024); //单位为M
        }
    }

    /**
     * 判断请求是否为multipart类型
     */
    public static boolean isMultipart(HttpServletRequest request) {
        return ServletFileUpload.isMultipartContent(request);
    }

    public static List<Object> createMultipartParamList(HttpServletRequest request) throws Exception {
        //TODO 
        return null;
    }

}
