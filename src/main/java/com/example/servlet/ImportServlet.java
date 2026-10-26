package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.entity.User;
import com.example.service.StatsService;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/import")
public class ImportServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/import.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(req)) {
            req.setAttribute("errorMsg", "请选择文件");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    // 使用转换流指定UTF-8编码读取上传文件
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(item.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        boolean firstLine = true;
                        int successCount = 0;
                        while ((line = reader.readLine()) != null) {
                            if (firstLine) {
                                firstLine = false;
                                continue; // 跳过表头
                            }
                            String[] fields = line.split(",");
                            if (fields.length >= 2) {
                                String name = fields[1].trim();
                                int age = Integer.parseInt(fields[2].trim());
                                User user = new User(0, name, age);
                                if (userDAO.addUser(user)) {
                                    successCount++;
                                }
                            }
                        }
                        StatsService.increment("add"); // 批量导入累加一次add计数
                        req.setAttribute("successMsg", "导入成功，共添加 " + successCount + " 条记录");
                        req.getRequestDispatcher("/WEB-INF/jsp/import.jsp").forward(req, resp);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            req.setAttribute("errorMsg", "导入失败：" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}