<%--
  Created by IntelliJ IDEA.
  User: NCC
  Date: 11/7/2023
  Time: 9:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Nhập thông tin</h1>
<form action="calculaeservlet" method="POST">
    <label>Product Description:</label>
    <input type="text" name="description" />
    <br>
    <label>Price:</label>
    <input type="number" name="price">
    <br>
    <label>Discount Percent: </label>
    <input type="number" name="percent">
    <br>
    <button type="submit">Tính chiết khấu</button>
</form>
</body>
</html>
