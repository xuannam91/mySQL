<%--
  Created by IntelliJ IDEA.
  User: NCC
  Date: 11/9/2023
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hiện thông tin</h2>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>tên</th>
            <th>tuoi</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${list}" var="item">
            <tr>
                <td>${item.customerID}</td>
                <td>${item.customerName}</td>
                <td>${item.age}</td>
            </tr>
        </c:forEach>

    </tbody>
</table>
</body>
</html>
