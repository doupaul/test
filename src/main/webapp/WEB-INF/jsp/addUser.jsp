<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/4/7
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<html>
<head>
    <title>新增人员</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="/static/login/js/jquery.min.js"></script>
    <script>
        // 判断是登录账号和密码是否为空,无法组织，解决不了
        function check(){
            var username = $("#username").val();
            var password = $("#password").val();
            if(username=="" || password==""){
                $("#message").text("名称或密码不能为空！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>新增人员</small>
                </h1>
            </div>
        </div>
    </div>
    <font color="red"><span id="message"></span></font>
    <form action="${path}/user/addUser" method="post" onsubmit="return check()">
        人员名称：<input type="text" id="username" name="username"><br><br><br>
        密　　码：<input type="password" id="password" name="password"><br><br><br>
        <input type="submit" value="添加" >
    </form>
    <a href="${path}/user/allUser">返回列表</a>

</div>
