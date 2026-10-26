package com.example.servlet;

import com.example.service.StatsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int[] stats = StatsService.readStats();
        req.setAttribute("addCount", stats[0]);
        req.setAttribute("deleteCount", stats[1]);
        req.setAttribute("updateCount", stats[2]);
        req.setAttribute("queryCount", stats[3]);
        req.getRequestDispatcher("/WEB-INF/jsp/stats.jsp").forward(req, resp);
    }
}