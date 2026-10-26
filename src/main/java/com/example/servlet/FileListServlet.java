package com.example.servlet;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/fileList")
public class FileListServlet extends HttpServlet {
    private static final String SAVE_PATH = "D:/webfile/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        File dir = new File(SAVE_PATH);
        List<Map<String,String>> fileList = new ArrayList<>();

        if(dir.exists()){
            File[] files = dir.listFiles();
            if(files != null){
                for (File f : files){
                    Map<String,String> map = new HashMap<>();
                    map.put("fileName",f.getName());
                    fileList.add(map);
                }
            }
        }
        // 转json返回
        String json = JSONArray.toJSONString(fileList);
        response.getWriter().write(json);
    }
}