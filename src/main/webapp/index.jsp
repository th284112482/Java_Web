<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>后台管理系统</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        html, body {
            height: 100%;
            width: 100%;
            overflow: hidden;
        }
        iframe {
            border: none;
            display: block;
        }
    </style>
</head>
<body>
    <!-- 顶部 -->
    <iframe src="header.jsp" name="headerFrame" width="100%" height=500px" frameborder="0" scrolling="no"></iframe>

    <div style="display: flex; height: calc(100% - 70px);">
        <!-- 左侧导航 -->
        <iframe src="sidebar.jsp" name="sidebarFrame" width="220px" height="100%" frameborder="0" scrolling="no"></iframe>
        <!-- 右侧内容 -->
        <iframe src="content.jsp" name="mainFrame" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>
    </div>
</body>
</html>