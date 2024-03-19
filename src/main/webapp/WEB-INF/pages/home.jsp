<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Male_Fashion Template">
        <meta name="keywords" content="Male_Fashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Home Page</title>
        <jsp:include page="include/css-page.jsp"/>
    <style>
        body{
            background: gray;
        }
        .nav{
            width: 100%;
            color: red;
            padding-top: 50px;
        }
    </style>

    </head>

    <body>
       <jsp:include page="include/header.jsp"/>
       <div class="container" style="display: flex; width: 100%;  font-weight: bold; color: white; background-color:  black;">
               <div style="width: 10%;">

               </div>
               <div style=" display:flex;  height: 50px; width: 80%; padding-top: 10px; margin-top: 50px;">
                   <div style="width: 60%;">
                       <ul  style="display: flex;">
                           <li style=" list-style: none;">
                               <a href="/movie" style="list-style-type: none; margin-right: 100px; text-decoration: none;">
                                   Phim
                               </a>
                           </li>
                           <li style=" list-style: none;">
                               <a href=""  style="list-style-type: none;  text-decoration: none;">
                                   Lịch chiếu
                               </a>
                           </li>
                       </ul>
                  </div>

                  <form:form action="/movie/search" method="post">
                        <input name="searchInput" type="text"  placeholder="Tìm kiếm" >
                        <button type="submit">Search</button>
                      </div>
                  </form:form>
               </div>
               <div style="width: 10%;">

               </div>
           </div>
           <div class="container">
                   <a href="/admin/addMovie" class="btn btn-primary">Add Movie</a>

                   <div class="row">
                       <div class="col"></div>
                       <div class="col-10">
                           <h3 class="mt-4">Danh sách phim</h3>

                           <div class="row row-cols-1 row-cols-md-3 g-4">
                               <c:forEach var="movie" items="${movieList}">
                                   <div class="col">
                                       <div class="card h-100">
                                           <img src="${movie.photo}" class="card-img-top" alt="...">
                                           <div class="card-body">
                                               <h5 class="card-title">${movie.movieName}</h5>
                                               <a href="/movie/${movie.movieId}" class="btn btn-primary">Detail</a>
                                               <a href="" class="btn btn-primary" style="background-color: #ffb6c1;">Đặt vé</a>
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
    </body>

</html>
