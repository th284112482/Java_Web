<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理 - SimpleCRUD</title>
    <!-- Bootstrap 5 CSS (CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome 6 (可选，用于图标) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fc;
        }
        .card-header {
            background-color: #4e73df;
            color: white;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f3f9;
            cursor: pointer;
        }
        .action-buttons .btn {
            margin: 0 2px;
        }
        .toast-container {
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1050;
        }
    </style>
</head>
<body>

<div class="container-fluid px-4 py-4">
    <div class="row justify-content-center">
        <div class="col-lg-10 col-md-12">
            <!-- 卡片式布局 -->
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header py-3 rounded-top-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <h4 class="mb-0"><i class="fas fa-users me-2"></i>用户管理</h4>
                        <a href="${pageContext.request.contextPath}/user?action=add" class="btn btn-light btn-sm">
                            <i class="fas fa-plus-circle"></i> 新增用户
                        </a>
                    </div>
                </div>
                <div class="card-body p-4">
                    <!-- 操作成功/失败提示区域（Bootstrap Alert） -->
                    <c:if test="${not empty successMsg}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <i class="fas fa-check-circle me-2"></i> ${successMsg}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                        <% session.removeAttribute("successMsg"); request.removeAttribute("successMsg"); %>
                    </c:if>
                    <c:if test="${not empty errorMsg}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="fas fa-exclamation-triangle me-2"></i> ${errorMsg}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                        <% session.removeAttribute("errorMsg"); request.removeAttribute("errorMsg"); %>
                    </c:if>

                    <!-- 用户表格 -->
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover align-middle">
                            <thead class="table-light">
                                <tr class="text-center">
                                    <th style="width: 10%">ID</th>
                                    <th style="width: 40%">姓名</th>
                                    <th style="width: 20%">年龄</th>
                                    <th style="width: 30%">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${userList}">
                                    <tr class="text-center">
                                        <td>${user.id}</td>
                                        <td>${user.name}</td>
                                        <td>${user.age}</td>
                                        <td class="action-buttons">
                                            <a href="${pageContext.request.contextPath}/user?action=edit&id=${user.id}" class="btn btn-sm btn-outline-primary">
                                                <i class="fas fa-edit"></i> 编辑
                                            </a>
                                            <a href="${pageContext.request.contextPath}/user?action=delete&id=${user.id}" 
                                               class="btn btn-sm btn-outline-danger"
                                               onclick="return confirm('确定要删除用户 ${user.name} 吗？');">
                                                <i class="fas fa-trash-alt"></i> 删除
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty userList}">
                                    <tr>
                                        <td colspan="4" class="text-center text-muted py-4">
                                            <i class="fas fa-database me-2"></i>暂无数据，请点击“新增用户”添加。
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>

                    <!-- 扩展功能按钮组（六种I/O流操作） -->
                    <hr class="my-4">
                    <div class="d-flex flex-wrap justify-content-center gap-2">
                        <a href="${pageContext.request.contextPath}/export" class="btn btn-outline-secondary btn-sm">
                            <i class="fas fa-file-csv"></i> 导出CSV
                        </a>
                        <a href="${pageContext.request.contextPath}/import" class="btn btn-outline-secondary btn-sm">
                            <i class="fas fa-upload"></i> 导入CSV
                        </a>
                        <a href="${pageContext.request.contextPath}/backup?action=backup" class="btn btn-outline-secondary btn-sm">
                            <i class="fas fa-database"></i> 备份用户
                        </a>
                        <a href="${pageContext.request.contextPath}/backup?action=restore" class="btn btn-outline-secondary btn-sm" onclick="return confirm('恢复将覆盖当前数据，确定吗？');">
                            <i class="fas fa-undo-alt"></i> 恢复备份
                        </a>
                        <a href="${pageContext.request.contextPath}/stats" class="btn btn-outline-secondary btn-sm">
                            <i class="fas fa-chart-simple"></i> 操作统计
                        </a>
                    </div>
                </div>
                <div class="card-footer text-muted text-center small">
                    基于 Bootstrap 5 美化 | 增删改查 + 六种I/O流示例
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS Bundle (包含 Popper) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // 自动关闭 Alert（可选，5秒后淡出）
    window.addEventListener('DOMContentLoaded', function() {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(alert => {
            setTimeout(() => {
                const bsAlert = new bootstrap.Alert(alert);
                bsAlert.close();
            }, 5000);
        });
    });
</script>
</body>
</html>