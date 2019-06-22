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
<!DOCTYPE HTML>
<html>
<head>
    <title>Paper列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="/static/login/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <c:if test="${search_flag == null}">
                    <h1>
                        <small>论文列表 —— 显示所有论文</small>
                    </h1>
                </c:if>
                <c:if test="${search_flag == true}">
                    <h1>
                        <small>论文模糊查询列表 —— 查询关键字：${paperName}</small>
                    </h1>
                </c:if>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <form action="${appPath}/paper/search" method="post">
                    <c:if test="${search_flag == null}">
                        <input type="text" id="paperName" name="paperName">
                        <input type="submit" value="模糊查询">
                    </c:if>
                    <c:if test="${search_flag == true}">
                        <a href="${appPath}/paper/allPaper">取消搜索</a>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${appPath}/paper/toAddPaper">新增</a>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <p style="color: red">${message}</p>
            <c:if test="${not empty requestScope.get('list')&& not empty search_flag}">
                <form action="${appPath}/paper/delpush" method="post">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="chbAll" onclick="chk()"></th>
                        <th>论文编号</th>
                        <th>论文名字</th>
                        <th>论文数量</th>
                        <th>论文详情</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="paper" items="${requestScope.get('list')}" varStatus="status">
                        <tr>
                            <th><input type="checkbox" name="ids" value="${paper.paperId}"></th>
                            <td>${paper.paperId}</td>
                            <td>${paper.paperName}</td>
                            <td>${paper.paperNum}</td>
                            <td>${paper.paperDetail}</td>
                            <td>
                                <a href="${appPath}/paper/toUpdatePaper?id=${paper.paperId}" onclick="return confirm('是否更改？')">更改</a> |
                                <a href="${appPath}/paper/del/${paper.paperId}" onclick="return confirm('是否删除？')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                    <input type="submit"  value="删除选择项" class="btn" onclick="return confirm('是否全部删除？')"/>

                    <div style="margin: 0 auto;float: right;">
                        <c:choose>
                            <c:when test="${page.pageNum le 1}">
                                <a href="${appPath}/paper/search?pageNum=1&&paperName=${paperName}">上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${appPath}/paper/search?pageNum=${page.pageNum - 1}&&paperName=${paperName}">上一页</a>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="i" begin="1" end="${page.pageTotal}" step="1">
                            <c:if test="${i ne page.pageNum}">
                                <a href="${appPath}/paper/search?pageNum=${i}&&paperName=${paperName}">${i}</a>
                            </c:if>
                            <c:if test="${i eq page.pageNum}">
                                ${i}
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${page.pageNum ge page.pageTotal}">
                                <a href="${appPath}/paper/search?pageNum=${page.pageTotal}&&paperName=${paperName}">下一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${appPath}/paper/search?pageNum=${page.pageNum + 1}&&paperName=${paperName}">下一页</a>
                            </c:otherwise>
                        </c:choose>
                        <span style="margin-left:10px;">
                             一共${page.pageTotal}页
                        </span>
                    </div>
                </form>
                <script>
                    function chk() {
                        var chbAll = document.getElementById("chbAll");
                        var ids = document.getElementsByName("ids");
                        if(chbAll.checked==true){
                            if(ids.length){
                                for (var i = 0;i < ids.length;i++)
                                    ids[i].checked=true;
                            }
                        }else {
                            if(ids.length){
                                for (var i = 0;i < ids.length;i++)
                                    ids[i].checked=false;
                            }
                        }
                    }
                </script>
            </c:if>
            <c:if test="${not empty requestScope.get('list')&& empty search_flag}">
                <form action="${appPath}/paper/delpush" method="post">
                    <table class="table table-hover table-striped">
                        <thead>
                        <tr>
                            <th><input type="checkbox" id="chbAll" onclick="chk()"></th>
                            <th>论文编号</th>
                            <th>论文名字</th>
                            <th>论文数量</th>
                            <th>论文详情</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="paper" items="${requestScope.get('list')}" varStatus="status">
                            <tr>
                                <th><input type="checkbox" name="ids" value="${paper.paperId}"></th>
                                <td>${paper.paperId}</td>
                                <td>${paper.paperName}</td>
                                <td>${paper.paperNum}</td>
                                <td>${paper.paperDetail}</td>
                                <td>
                                    <a href="${appPath}/paper/toUpdatePaper?id=${paper.paperId}" onclick="return confirm('是否更改？')">更改</a> |
                                    <a href="${appPath}/paper/del/${paper.paperId}" onclick="return confirm('是否删除？')">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit"  value="删除选择项" class="btn" onclick="return confirm('是否全部删除？')"/>

                    <div style="margin: 0 auto;float: right;">
                        <c:choose>
                            <c:when test="${page.pageNum le 1}">
                                <a href="${appPath}/paper/allPaper?pageNum=1">上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${appPath}/paper/allPaper?pageNum=${page.pageNum - 1}">上一页</a>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="i" begin="1" end="${page.pageTotal}" step="1">
                            <a href="${appPath}/paper/allPaper?pageNum=${i}">${i}</a>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${page.pageNum ge page.pageTotal}">
                                <a href="${appPath}/paper/allPaper?pageNum=${page.pageTotal}">下一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${appPath}/paper/allPaper?pageNum=${page.pageNum + 1}">下一页</a>
                            </c:otherwise>
                        </c:choose>
                        <span style="margin-left:10px;">
                             一共${page.pageTotal}页
                        </span>
                    </div>
                </form>
                <script>
                    function chk() {
                        var chbAll = document.getElementById("chbAll");
                        var ids = document.getElementsByName("ids");
                        if(chbAll.checked==true){
                            if(ids.length){
                                for (var i = 0;i < ids.length;i++)
                                    ids[i].checked=true;
                            }
                        }else {
                            if(ids.length){
                                for (var i = 0;i < ids.length;i++)
                                    ids[i].checked=false;
                            }
                        }
                    }
                </script>
            </c:if>
            <c:if test="${empty requestScope.get('list')}">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>论文编号</th>
                        <th>论文名字</th>
                        <th>论文数量</th>
                        <th>论文详情</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="5" align="center">暂无数据</td>
                        </tr>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>
