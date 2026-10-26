<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* 顶部导航栏 */
        .header {
            width: 100%;
            height: 60px;
            background: #2b3d51;
            color: #fff;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 30px;
            font-family: "Microsoft YaHei", sans-serif;
        }
        .logo {
            font-size: 22px;
            font-weight: bold;
        }
        .user-info {
            font-size: 14px;
            color: #dcdfe6;
        }

        /* 轮播图高度 */
        .carousel-item {
            height: 400px;
        }
        .carousel-item img {
            object-fit: cover;
            height: 100%;
        }
    </style>
</head>
<body>

<!-- 顶部导航栏 -->
<div class="header">
    <div class="logo">🏢 【贵阳信息科技学院】图书管理系统</div>
    <div class="user-info">管理员：admin</div>
</div>

<!-- 你要的 轮播图结构（完全一样） -->
<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
    <ol class="carousel-indicators">
        <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
        <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
        <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://picsum.photos/1200/400?random=1" class="d-block w-100" alt="网络编程">
        </div>
        <div class="carousel-item">
            <img src="https://picsum.photos/1200/400?random=2" class="d-block w-100" alt="Web整合">
        </div>
        <div class="carousel-item">
            <img src="https://picsum.photos/1200/400?random=3" class="d-block w-100" alt="文件上传下载">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>