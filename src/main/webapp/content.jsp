<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>文件上传下载系统</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Microsoft YaHei",sans-serif;
        }
        body {
            background-color: #f5f7fa;
            padding: 30px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
        }
        .title-box {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.08);
            margin-bottom: 20px;
        }
        .card {
            background: #fff;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.08);
        }
        .card h3 {
            border-left: 4px solid #409eff;
            padding-left: 10px;
        }
        .upload-area {
            border: 2px dashed #ccc;
            padding: 40px 20px;
            text-align: center;
            border-radius: 6px;
            cursor: pointer;
        }
        .btn {
            background: #409eff;
            color: #fff;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        .file-list {
            border: 1px solid #eee;
            min-height: 200px;
            border-radius: 6px;
        }
        .file-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 12px 15px;
            border-bottom: 1px solid #eee;
        }
        .download { color: #67c23a; margin-right:10px; }
        .delete { color: #f56c6c; cursor:pointer; }
        .tip-text { text-align: center; line-height: 200px; color: #999; }
    </style>
</head>
<body>

<div class="container">
    <div class="title-box">
        <h2>🌐 文件上传下载系统</h2>
    </div>

    <div style="display:grid;grid-template-columns:1fr 1fr;gap:20px;">
        <div class="card">
            <h3>📤 上传文件</h3>
            <div class="upload-area" id="uploadArea">
            
                <input type="file" id="file-input" multiple>
            </div>
            <br>
            <button class="btn" id="uploadBtn">确认上传</button>
        </div>

        <div class="card">
            <h3>📥 文件列表</h3>
            <div class="file-list" id="fileList">
                <div class="tip-text">加载中...</div>
            </div>
        </div>
    </div>
</div>

<script>
    const ctx = "${pageContext.request.contextPath}";
    let selectedFiles = [];

    const uploadArea = document.getElementById("uploadArea");
    const fileInput = document.getElementById("file-input");
    const uploadBtn = document.getElementById("uploadBtn");
    const fileList = document.getElementById("fileList");

    uploadArea.onclick = () => fileInput.click();
    fileInput.onchange = (e) => {
        selectedFiles = e.target.files;
        alert("已选择：" + selectedFiles.length + " 个文件");
    };

    uploadBtn.onclick = () => {
        if (!selectedFiles.length) { alert("请选择文件"); return; }
        let fd = new FormData();
        for(let f of selectedFiles) fd.append("file", f);

        fetch(ctx + "/fileUpload", { method: "POST", body: fd })
            .then(res => res.json())
            .then(data => {
                alert(data.msg);
                getList();
            })
    };

    // ============== 关键：这里绝对不会让JSP报错 ==============
    function getList() {
        fetch(ctx + "/fileList")
            .then(res => res.json())
            .then(arr => {
                let html = "";
                if (arr.length === 0) {
                    html = '<div class="tip-text">暂无文件</div>';
                } else {
                    arr.forEach(item => {
                        let name = item.fileName;
                        let urlName = encodeURIComponent(name);
                        html += `
                        <div class="file-item">
                            <span>`+name+`</span>
                            <div>
                                <a href="`+ctx+`/fileDownload?name=`+urlName+`" target="_blank" class="download">下载</a>
                                <span onclick="delFile('`+urlName+`')" class="delete">删除</span>
                            </div>
                        </div>`;
                    });
                }
                fileList.innerHTML = html;
            })
    }

    function delFile(name) {
        if (!confirm("确定删除？")) return;
        fetch(ctx + "/fileDel?name=" + name)
            .then(() => getList());
    }

    window.onload = getList;
</script>
</body>
</html>