<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户管理</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        /* 浅色优雅背景：渐变 + 噪点 + 柔光 */
        body {
            margin: 0;
            padding: 0;
            min-height: 100vh;
            background: linear-gradient(135deg, #e0e5ec 0%, #f0f4fa 50%, #e6ecf5 100%);
            position: relative;
            font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
        }
        /* 细微的噪点纹理 (增强质感) */
        body::before {
            content: "";
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: radial-gradient(rgba(0, 0, 0, 0.02) 1px, transparent 1px);
            background-size: 20px 20px;
            pointer-events: none;
            z-index: 0;
        }
        /* 柔光光晕 */
        body::after {
            content: "";
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: radial-gradient(circle at 30% 40%, rgba(255, 255, 255, 0.6) 0%, transparent 40%),
                        radial-gradient(circle at 70% 80%, rgba(200, 220, 255, 0.5) 0%, transparent 50%);
            pointer-events: none;
            z-index: 0;
        }
        /* 内容层 */
        .container {
            position: relative;
            z-index: 1;
        }
        /* 卡片磨砂玻璃效果 */
        .card {
            background: rgba(255, 255, 255, 0.7) !important;
            backdrop-filter: blur(8px);
            border: 1px solid rgba(255, 255, 255, 0.6);
            border-radius: 2rem;
            box-shadow: 0 20px 30px -10px rgba(0, 0, 0, 0.15);
        }
        .card-header, .card-footer {
            background: transparent !important;
        }
        /* 表格半透明适应背景 */
        .table {
            --bs-table-bg: transparent;
        }
        .table th {
            background-color: rgba(0, 0, 0, 0.02);
            font-weight: 600;
            color: #2c3e50;
        }
        .table td {
            background-color: rgba(255, 255, 255, 0.3);
            color: #2c3e50;
        }
        .table-striped > tbody > tr:nth-of-type(odd) > * {
            background-color: rgba(240, 245, 255, 0.6);
        }
        .table-hover > tbody > tr:hover > * {
            background-color: rgba(230, 240, 255, 0.8);
        }
        /* 按钮保持清晰 */
        .btn-primary {
            background-color: #0d6efd;
            border: none;
            box-shadow: 0 4px 8px rgba(13, 110, 253, 0.2);
        }
        .btn-warning {
            background-color: #ffc107;
            border: none;
            color: #000;
        }
        .btn-danger {
            background-color: #dc3545;
            border: none;
        }
        /* 提示框适配 */
        .alert-info {
            background-color: rgba(209, 236, 241, 0.9);
            border-color: rgba(190, 229, 235, 0.8);
        }
    </style>
</head>
<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10 col-sm-12">
                <div class="card border-0">
                    <div class="card-header bg-white border-0 pt-4 pb-0">
                        <div class="d-flex justify-content-between align-items-center">
                            <h2 class="h4 mb-0 text-secondary">
                                <i class="bi bi-people-fill me-2"></i>用户管理
                            </h2>
                            <a href="${pageContext.request.contextPath}/user?action=add" class="btn btn-primary btn-sm">
                                <i class="bi bi-plus-circle me-1"></i>添加用户
                            </a>
                        </div>
                    </div>
                    <div class="card-body p-4">
                        <c:if test="${empty userList}">
                            <div class="alert alert-info text-center" role="alert">
                                <i class="bi bi-info-circle me-2"></i>暂无用户数据，请点击上方按钮添加。
                            </div>
                        </c:if>
                        <c:if test="${not empty userList}">
                            <div class="table-responsive">
                                <table class="table table-hover table-striped align-middle mb-0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>姓名</th>
                                            <th>年龄</th>
                                            <th class="text-center">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${userList}">
                                            <tr>
                                                <td>${user.id}</td>
                                                <td>${user.name}</td>
                                                <td>${user.age}</td>
                                                <td class="text-center">
                                                    <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}" 
                                                       class="btn btn-sm btn-warning me-1" 
                                                       title="编辑">
                                                        <i class="bi bi-pencil-square me-1"></i>编辑
                                                    </a>
                                                    <a href="${pageContext.request.contextPath}/user?action=delete&id=${user.id}" 
                                                       class="btn btn-sm btn-danger" 
                                                       onclick="return confirm('确定要删除该用户吗？')"
                                                       title="删除">
                                                        <i class="bi bi-trash3 me-1"></i>删除
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </div>
                    <div class="card-footer bg-white border-0 text-muted small text-end p-3">
                        共 <strong>${userList.size()}</strong> 条记录
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS (可选) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>