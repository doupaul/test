<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<% pageContext.setAttribute("appPath", request.getContextPath()); %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>

    <span>上次上传了：
        <c:if test="${empty fileNames}">未上传任何文件</c:if>
        <c:if test="${not empty fileNames}">${fileNames}</c:if>
    </span>
    <br>
    <form action="${appPath}/upload" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
    <div class="col-md-12">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right">文件域：</label>
            <div class="col-sm-9">
                <input type="file"  name="file" />
                <input type="file"  name="file" />
            </div>
            <br>
            <input type="submit" value="点击上传">
        </div>
    </div>
</form>
</body>
</html>
