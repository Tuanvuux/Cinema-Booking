<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>View Movie</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">


</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col"></div>
            <div class="col-10">
                <a href="/admin/addMovie" class="btn btn-primary">Add Movie</a>

                <div class="row row-cols-1 row-cols-md-3 g-4">
                    <c:forEach var="movie" items="${movieList}">
                        <div class="col">
                            <div class="card h-100">
                                <img src="${movie.photo}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">${movie.movieName}</h5>
                                    <a href="/movie/detail?id=${movie.movieId}" class="btn btn-primary">Detail</a>
                                    <a href="/admin/editMovie?id=${movie.movieId}" class="btn btn-success">Edit</a>
                                     <a href="/admin/deleteMovie?movieId=${movie.movieId}" class="btn btn-danger">Delete</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <c:if test="${empty movieList}">
                    <div class="alert alert-warning mt-4" role="alert">There is no data</div>
                </c:if>
            </div>
            <div class="col"></div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
