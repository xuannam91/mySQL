<%--
  Created by IntelliJ IDEA.
  User: NCC
  Date: 11/7/2023
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Student List</h1>
<Table>
    <thead>
    <tr>
        <th>Student Code</th>
        <th>Full Name</th>
        <th>Age</th>
        <th>Sex</th>
    </tr>
    </thead>
    <TBODY>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.studentId}</td>
            <td>${student.fullName}</td>
            <td>${student.age}</td>
            <td>${student.sex? "male" : "female"}</td>
        </tr>
    </c:forEach>

    </TBODY>
</Table>
</body>
</html>
