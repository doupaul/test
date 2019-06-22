<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<% pageContext.setAttribute("appPath", request.getContextPath()); %>
<html>
<head>
    <title>文件下载</title>
</head>
<body>
    <a href="${appPath}/download/internal">内部地址下载</a>
    <a href="${appPath}/download/out">外部地址下载</a>
</body>
</html>
