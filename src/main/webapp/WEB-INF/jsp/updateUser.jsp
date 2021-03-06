<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<html>
<head>
    <title>修改人员</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改人员</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="" name="userForm">
        <input type="hidden" name="id" value="${user.id}"/>
        人员名称：<input type="text" name="username" value="${user.username}"/>
        密　　码：<input type="password" name="password" value="${user.password}"/>
        <input type="button" value="提交" onclick="updateUser()"/>
    </form>
    <a href="${path}/user/allUser">返回列表</a>
    <script type="text/javascript">
        function updateUser() {
            var form = document.forms[0];
            form.action = "${path}/user/updateUser";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
