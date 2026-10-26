package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.entity.User;
import com.example.util.FilePathUtil;
import com.example.service.StatsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/export")
public class ExportServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String csvPath = FilePathUtil.getCsvExportPath();
        // 使用字符流、缓冲流、打印流写入CSV
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(csvPath), StandardCharsets.UTF_8)))) {
            // 写入CSV头
            pw.println("ID,姓名,年龄");
            List<User> users = userDAO.getAllUsers();
            for (User u : users) {
                pw.printf("%d,%s,%d%n", u.getId(), u.getName(), u.getAge());
            }
            StatsService.increment("query"); // 导出也算一次查询
        } catch (Exception e) {
            req.setAttribute("errorMsg", "导出失败：" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
            return;
        }

        // 设置响应头，下载文件
        resp.setContentType("text/csv;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename=users.csv");
        try (InputStream is = new FileInputStream(csvPath);
             OutputStream os = resp.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        }
    }
}