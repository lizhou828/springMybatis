<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    ${userName}，你好!<br/>
    所有用户：<br/>
    <table border="1px">
        <tr>
            <td width="100">ID</td>
            <td width="200">用户名</td>
            <td width="300">邮箱地址</td>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>