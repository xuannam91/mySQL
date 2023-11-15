<%--
  Created by IntelliJ IDEA.
  User: NCC
  Date: 11/13/2023
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>Class Name</td>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${classRooms}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>
                <a href="">Delete</a>
                <a href="">Update</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
