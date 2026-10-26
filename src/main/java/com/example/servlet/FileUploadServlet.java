package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/fileUpload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    // 文件存储路径
    private static final String SAVE_PATH = "D:/webfile/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 创建保存目录
        File dir = new File(SAVE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // 获取上传文件
        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        // 保存文件
        part.write(SAVE_PATH + fileName);

        // 返回JSON
        response.getWriter().write("{\"code\":200,\"msg\":\"上传成功\"}");
    }
}