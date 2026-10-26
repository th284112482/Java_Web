<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>导入用户CSV</title></head>
<body>
<h2 style="text-align:center;">导入CSV文件</h2>
<div style="margin:0 auto; width:50%;">
    <form action="${pageContext.request.contextPath}/import" method="post" enctype="multipart/form-data">
        <input type="file" name="csvFile" accept=".csv" required><br><br>
        <input type="submit" value="上传并导入">
        <a href="${pageContext.request.contextPath}/user?action=list">返回</a>
    </form>
    <p style="color:green;">${successMsg}</p>
</div>
</body>
</html>