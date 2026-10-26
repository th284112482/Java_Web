package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.entity.User;
import com.example.service.StatsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                // 跳转到添加页面
                req.getRequestDispatcher("/WEB-INF/jsp/addUser.jsp").forward(req, resp);
                
                break;
            case "edit":
                // 获取要编辑的用户ID，查询后跳转到编辑页面
                String idStr = req.getParameter("id");
                if (idStr != null) {
                    int id = Integer.parseInt(idStr);
                    User user = userDAO.getUserById(id);
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/WEB-INF/jsp/editUser.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/user?action=list");
                }
                break;
            case "delete":
                // 执行删除操作
                String deleteId = req.getParameter("id");
                if (deleteId != null) {
                    userDAO.deleteUser(Integer.parseInt(deleteId));
                    StatsService.increment("delete"); // 增加删除计数
                }
                resp.sendRedirect(req.getContextPath() + "/user?action=list");
                break;
            case "list":
            default:
                // 查询所有用户，跳转到列表页
            	try {
	                List<User> userList = userDAO.getAllUsers();
	                StatsService.increment("query"); // 增加查询计数
	                req.setAttribute("userList", userList);
	                req.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(req, resp);
            	} catch (Exception e) {
                    e.printStackTrace();
                    req.setAttribute("errorMsg", "添加用户失败：" + e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 处理中文乱码
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            // 处理添加请求
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            User user = new User(0, name, age); // id自增，设为0
            userDAO.addUser(user);
            StatsService.increment("add"); // 增加计数
            resp.sendRedirect(req.getContextPath() + "/user?action=list");
        } else if ("update".equals(action)) {
            // 处理更新请求
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            User user = new User(id, name, age);
            userDAO.updateUser(user);
            StatsService.increment("update"); // 增加计数
            resp.sendRedirect(req.getContextPath() + "/user?action=list");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user?action=list");
//            resp.sendRedirect(req.getContextPath() + "/user?action=list");
        }
    }
}