<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <style>
        form { width: 50%; margin: 50px auto; }
        label { display: inline-block; width: 60px; }
        input[type="text"], input[type="number"] { width: 200px; padding: 5px; margin: 10px 0; }
        input[type="submit"] { margin-left: 70px; padding: 8px 20px; }

        #bottom1{
            border: solid 1px red;
            padding: 8px;
            margin: 10px 0;
            cursor: pointer;
        }
        #bottom2{
            border: solid 1px green;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        #bottom3{
            width: 180px;
            border: solid 1px red;
            padding: 8px;
            min-height: 30px;
        }
        #bottom4{
            width: 180px;
            border: solid 1px red;
            padding: 8px;
        }
    </style>
    <script>
        // 页面加载完毕执行
        window.onload = function(){
            alert("页面加载完成，开始添加用户！");
        }

        // 点击 bottom1 触发事件
        function clickBox(){
            alert("你点击了测试区域1");
        }
  
        // 清空表单
        function resetForm(){
            document.getElementsByName("name")[0].value = "";
            document.getElementsByName("age")[0].value = "";
        }
    </script>
</head>
<body>
    <h2 style="text-align: center;">添加用户</h2>
    <form action="${pageContext.request.contextPath}/user" method="post">
        <input type="hidden" name="action" value="add">
        <div>
            <label>姓名：</label>
            <input type="text" name="name" required>
        </div>
        <div>
            <label>年龄：</label>
            <input type="number" name="age" min="0" required>
        </div>
        <div>
            <input type="submit" value="提交">
            <!-- 表单内提交按钮后添加重置按钮 -->

			<input type="button" value="清空" onclick="resetForm()">
            <a href="${pageContext.request.contextPath}/user?action=list">返回</a>
        </div>





        <!-- 绑定点击事件 -->
        <div id="bottom1" onclick="clickBox()">测试部分1</div>
        <div id="bottom2">
            <div id="bottom3">测试部分3</div>
            <div id="bottom4">测试部分4</div>
        </div>
    </form>
</body>
</html>