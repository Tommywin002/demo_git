<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 17/05/2022
  Time: 10:30 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sach san pham: </h1></br>
<a href="products?action=create">Tao san pham moi</a>
<form >
    <input type="text" name="find">
    <button>Find</button>
</form>
<c:forEach items='${ds}' var="SanPham">
    <h1>id: ${SanPham.id} Name: ${SanPham.name} Price: ${SanPham.price} , <a href="/products?action=edit&id=${SanPham.id}">Fix</a> </h1>
</c:forEach>
<a href="/products?order=true">Sap xep</a>
</body>
</html>
