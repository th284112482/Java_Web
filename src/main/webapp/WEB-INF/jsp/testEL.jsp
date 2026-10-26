<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
    <style>
        table { border-collapse: collapse; width: 60%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; margin-right: 10px; }
    </style>
</head>
<body>
    <h2 style="text-align: center;">测试区域</h2>
    <div style="text-align: center;color:red;">
        ${el}
        <%
// 这些全是 JSP 内置对象，服务器自动创建，直接用
request.setAttribute("a", "1");
session.setAttribute("b", "2");
application.setAttribute("siteName", "JSP实验系统"); // 直接用
out.print("网站名：" + application.getAttribute("siteName"));
%>
    </div>
    
</body>
</html>