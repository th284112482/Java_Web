<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑用户</title>
    <style>
        form { width: 30%; margin: 50px auto; }
        label { display: inline-block; width: 60px; }
        input[type="text"], input[type="number"] { width: 200px; padding: 5px; margin: 10px 0; }
        input[type="submit"] { margin-left: 70px; padding: 8px 20px; }
    </style>
</head>
<body>
    <h2 style="text-align: center;">编辑用户</h2>
    <form action="${pageContext.request.contextPath}/user" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${user.id}">
        <div>
            <label>姓名：</label>
            <input type="text" name="name" value="${user.name}" required>
        </div>
        <div>
            <label>年龄：</label>
            <input type="number" name="age" value="${user.age}" min="0" required>
        </div>
        <div>
            <input type="submit" value="更新">
            <a href="${pageContext.request.contextPath}/user?action=list">返回</a>
        </div>
    </form>
</body>
</html>