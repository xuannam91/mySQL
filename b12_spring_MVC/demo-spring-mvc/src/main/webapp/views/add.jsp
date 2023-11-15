<%--
  Created by IntelliJ IDEA.
  User: NCC
  Date: 11/15/2023
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/account/store" method="post" modelAttribute="account">
    <label for="name">Username</label>
    <form:input type="text" id="name" path="userName"/>
    <label for="name">Password</label>
    <form:input type="text" id="password" path="passWord"/>
    <button type="submit">ADD</button>

</form:form>
</body>
</html>
