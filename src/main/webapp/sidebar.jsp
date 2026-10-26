<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .sidebar {
        width: 100%;
        height: 100vh;
        background: #2f4050;
        font-family: "Microsoft YaHei", sans-serif;
        padding-top: 10px;
    }
    .menu-item {
        display: block;
        padding: 14px 20px;
        color: #c7c7c7;
        text-decoration: none;
        font-size: 14px;
        transition: all 0.2s;
    }
    .menu-item:hover {
        background: #24323f;
        color: #fff;
        padding-left: 25px;
    }
    /* 点击后高亮样式 */
    .menu-item.active {
        background: #1ab394 !important;
        color: #fff !important;
        border-left: 4px solid #fff;
    }
</style>

<div class="sidebar">
    <a href="content.jsp" target="mainFrame" class="menu-item active">📊 文件管理</a>
    <a href="${pageContext.request.contextPath}/user?action=list" target="mainFrame" class="menu-item">👤 用户管理</a>
    <a href="role.jsp" target="mainFrame" class="menu-item">🔐 角色管理</a>
    <a href="menu.jsp" target="mainFrame" class="menu-item">📋 菜单管理</a>
    <a href="log.jsp" target="mainFrame" class="menu-item">📜 系统日志</a>
    <a href="config.jsp" target="mainFrame" class="menu-item">⚙️ 系统设置</a>
</div>

<script>
    // 菜单点击高亮
    document.querySelectorAll('.menu-item').forEach(item => {
        item.onclick = () => {
            document.querySelectorAll('.menu-item').forEach(i => i.classList.remove('active'))
            item.classList.add('active')
        }
    })
</script>