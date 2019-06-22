<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/4/6
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<% pageContext.setAttribute("appPath", request.getContextPath()); %>
<html>
<head>
    <title>人员列表</title>
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
                    <small>人员列表 —— 显示所有人员</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <form action="${appPath}/user/search" method="post">
                    <c:if test="${search_flag == null}">
                        <input type="text" id="username" name="username">
                        <input type="submit" value="模糊查询">
                    </c:if>
                    <c:if test="${search_flag == true}">
                        <a href="${appPath}/user/allUser">取消搜索</a>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${appPath}/user/toAddUser">新增</a>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>人员编号</th>
                    <th>人员名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>
                            <a href="${appPath}/user/toUpdateUser?id=${user.id}" onclick="return confirm('是否更改？')">更改</a> |
                            <a href="${appPath}/user/del/${user.id}" onclick="return confirm('是否删除？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
