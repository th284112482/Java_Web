package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@WebServlet({"/fileDownload", "/fileDel"})
public class FileOperateServlet extends HttpServlet {
    private static final String SAVE_PATH = "D:/webfile/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String fileName = request.getParameter("name");
        String servletPath = request.getServletPath();
        File file = new File(SAVE_PATH, fileName);

        // 删除文件
        if ("/fileDel".equals(servletPath)) {
            response.setContentType("application/json;charset=UTF-8");
            if (file.exists() && file.delete()) {
                response.getWriter().write("{\"code\":200,\"msg\":\"删除成功\"}");
            } else {
                response.getWriter().write("{\"code\":500,\"msg\":\"删除失败\"}");
            }
            return;
        }

        // 下载文件
        if ("/fileDownload".equals(servletPath)) {
            if (!file.exists()) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("文件不存在");
                return;
            }

            String encodeName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodeName);
            response.setContentType("application/octet-stream");

            FileInputStream fis = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            out.flush();
            out.close();
            fis.close();
        }
    }
}