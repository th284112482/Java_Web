package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.entity.User;
import com.example.util.DBUtil;
import com.example.util.FilePathUtil;
import com.example.service.StatsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet("/backup")
public class BackupServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("restore".equals(action)) {
            restore(req, resp);
        } else {
            backup(req, resp);
        }
    }

    private void backup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userDAO.getAllUsers();
            // 使用对象输出流序列化List<User>
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(FilePathUtil.getSerBackupPath()))) {
                oos.writeObject(users);
            }
            StatsService.increment("query");
            req.setAttribute("successMsg", "备份成功！");
            req.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMsg", "备份失败：" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }

    private void restore(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File backupFile = new File(FilePathUtil.getSerBackupPath());
        if (!backupFile.exists()) {
            req.setAttribute("errorMsg", "备份文件不存在，请先备份");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(backupFile))) {
            List<User> users = (List<User>) ois.readObject();
            // 清空表（显式声明 Connection 和 PreparedStatement）
            String deleteAll = "DELETE FROM user";
            try (Connection conn = DBUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(deleteAll)) {
                ps.executeUpdate();
            }
            // 重新插入
            for (User u : users) {
                userDAO.addUser(u);
            }
            StatsService.increment("add");
            req.setAttribute("successMsg", "恢复成功！");
            req.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMsg", "恢复失败：" + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}