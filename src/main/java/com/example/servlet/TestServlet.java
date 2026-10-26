package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDAO;
import com.example.entity.User;

import java.io.IOException;
import java.util.List;


@WebServlet("/test")
public class TestServlet  extends HttpServlet {
	private UserDAO userDAO = new UserDAO();
   
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        String action = req.getParameter("action");
        
//        if (action.equals("list")) {
        if (action.equals("el")) {

        	//获取列表
//             List<User> userList = userDAO.getAllUsers();
//             req.setAttribute("userList", userList);
        	//获取字符串
        	String content = "这是我的测试内容";
        	req.setAttribute("el", content);
        	//获取对象数据
//        	User u = new User();
//        	u.setAge(18);
//        	req.setAttribute("user", u);
        	//
        	
            req.getRequestDispatcher("/WEB-INF/jsp/testEL.jsp").forward(req, resp);
//             req.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").forward(req, resp);
        	
        }
        else
        {
        	req.setAttribute("errorMsg", "添加用户失败：");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
            	

    }
	
}