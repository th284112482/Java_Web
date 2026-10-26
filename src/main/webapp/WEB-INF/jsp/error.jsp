<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>系统错误</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 100px; }
        .error-box { border: 1px solid #ccc; background-color: #f9f9f9; padding: 20px; width: 60%; margin: 0 auto; border-radius: 5px; }
        h2 { color: #d9534f; }
        .message { color: #333; margin: 20px 0; }
        .back-link { margin-top: 20px; }
    </style>
</head>
<body>
    <div class="error-box">
        <h2>操作失败，系统发生错误,错误码500</h2>
        <div class="message">
            <c:choose>
                <c:when test="${not empty errorMsg}">
                    <p>错误原因：${errorMsg}</p>
                </c:when>
                <c:otherwise>
                    <p>未知错误，请联系管理员。</p>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/user?action=list">返回首页</a>
        </div>
    </div>
</body>
</html>