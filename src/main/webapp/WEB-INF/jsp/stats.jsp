<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>操作统计</title></head>
<body>
<h2 style="text-align:center;">累计操作次数（数据流记录）</h2>
<table border="1" style="margin:20px auto; width:300px;">
    <tr><th>操作类型</th><th>次数</th></tr>
    <tr><td>新增用户</td><td>${addCount}</td></tr>
    <tr><td>删除用户</td><td>${deleteCount}</td></tr>
    <tr><td>修改用户</td><td>${updateCount}</td></tr>
    <tr><td>查询用户</td><td>${queryCount}</td></tr>
</table>
<div style="text-align:center;">
    <a href="${pageContext.request.contextPath}/user?action=list">返回用户列表</a>
</div>
</body>
</html>