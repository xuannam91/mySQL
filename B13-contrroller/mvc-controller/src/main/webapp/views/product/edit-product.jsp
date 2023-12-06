<%--
  Created by IntelliJ IDEA.
  User: NCC
  Date: 11/16/2023
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <title>Add student</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h1 class="text-center">Sửa product</h1>
            <form:form modelAttribute="product" action="${pageContext.request.contextPath}/update-product/${product.id}" method="post" enctype="multipart/form-data">
                <form:input type="text" path="id" readonly="true"/>
                <div class="form-group">
                    <label for="pName">Product Name</label>
                    <form:input type="text" class="form-control" id="pName" path="nameProduct"  />
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <form:input type="number" class="form-control" id="price" path="price"  />
                </div>


                <form:select class="form-control" path="category.categoryId">
                        <form:options items="${categoryList}" itemValue="categoryId" itemLabel="categoryName" />
                </form:select><br>

                <div class="form-group">
                    <label >Image</label>
                    <input type="file" src="" alt="" name="img_upload">
                    <img src="<%=request.getContextPath()%>/uploads/images/${product.image}" alt=""/>
                </div>


                <button type="submit" class="btn btn-outline-dark">Update product</button>
            </form:form>
        </div>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>