<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Movie</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>

    </style>
</head>
<body>
    <h1>Movie Details</h1>
        <c:if var test="${not empty movie}">
            <h2>Tên phim : ${movie.title}</h2>
            <p>Mô tả: ${movie.description}</p>
            <p>Đạo diễn: ${movie.director}</p>
            <p>Nhà sản xuất: ${movie.producer}</p>
            <p>Diễn viên: ${movie.actor}</p>
            <p>Quốc gia: ${movie.nation}</p>
            <p>Trailer: ${movie.trailer}</p>
            <p>Thể loại: ${Category.category}</p>
        </c:if>
        <c:if test="${empty movie}">
            <p>Movie not found!</p>
        </c:if>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
