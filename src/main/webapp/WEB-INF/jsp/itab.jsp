<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table >
    <thead>
    <tr>
        <th>人员编号</th>
        <th>人员名称</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="i" items="${requestScope.get('itab')}" varStatus="status">
        <tr>
            <td>${i.iId}</td>
            <td>${i.iName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
