<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách phim</title>
    <style>

        .btn {
            border: 1px solid #031d2c;
            border-radius: 10px;
            text-decoration: none;
            color: #031d2c;
            padding: 5px 10px 0 10px;
            height: 27px;
            margin-bottom: 10px;
        }

        .btn:hover {
            background-color: #8d0000;
            border: 1px solid #ba1911;
            color: white;
        }

        .movie {
            width: fit-content;
            margin: 10px;
            border-radius: 10px;
            height: 320px;
        }

        .button {
            padding-bottom: 10px;
            border-radius: 1px solid gray;
            width: 220px;
        }

        .long-text {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>

</head>

<body style="background-color: #f2f5fb">
    <div class="navigation"
        style="display: flex; width: 100%; height: 150px; font-weight: bold; color: white; background-color:  #031d2c; color: white">
        <div style="width: 10%;">

        </div>
        <div
            style=" display:flex;  height: 50px; width: 80%; padding-top: 10px; margin-top: 50px; align-items: center;">
            <div style="width: 15%;">
                <img src="../../resources/img/cinema/logo.png" style="width: 100px;">
            </div>
            <div style="width: 85%;">
                <ul style="display: flex; justify-content: space-between; list-style: none;">
                    <li>
                        <a href="/" style=" margin-right: 100px; text-decoration: none; color: white;">
                            Trang chủ
                        </a>
                    </li>
                    <li>
                        <a href="/movie" style="list-style-type: none;  text-decoration: none; color: white;">
                            Phim
                        </a>
                    </li>
                    <li>
                        <a href="" style="list-style-type: none;  text-decoration: none; color: white;">
                            Lịch chiếu
                        </a>
                    </li>
                    <form:form action="/movie/search" method="post">
                        <input name="searchInput" type="text" placeholder="Tìm kiếm">
                        <button type="submit">Search</button>
                    </form:form>
                </ul>
            </div>
        </div>
        <div style="width: 10%;"></div>
    </div>





    <div class="film" style=" display: flex;flex-wrap: wrap;">
        <div style="width: 10%;"></div>
        <div class="main-home" style="width: 80%; margin-left: 100px;">
            <div style="margin: 0 auto; color: #031d2c;">
                <h1>Movie Details</h1>
                                <h2>Tên phim : ${movie.movieName}</h2>
                                <img src="getPhoto/<c:out value='${movie.movieId}'/>"
                                                                style=" width:220px; height: 270px; border-radius: 10px 10px 0px 0px;">
                                <p>Quốc gia: ${movie.nation}</p>
                                <p>Thể loại: ${movie.category.cateName}</p>
                                <p>Thời lượng: ${movie.timeSlot}</p>
                                <p>Đạo diễn: ${movie.director}</p>
                                <p>Nhà sản xuất: ${movie.producer}</p>
                                <p>Diễn viên: ${movie.actor}</p>
                                <p>Trailer: <a href="${movie.trailer}" style="color: #031d2c; text-decoration: none;">${movie.trailer}</a></p>
                                <p>Mô tả: ${movie.describeMovie}</p>

                                <div class="button"
                                    style="display: flex;flex-wrap: nowrap;justify-content: space-evenly; height: 25px;">
                                    <a href="/movie/detail?id=${movie.movieId}" class="btn">Chi tiết</a>
                                    <a href="/admin/editMovie?id=${movie.movieId}" class="btn">Sửa</a>
                                    <a href="/admin/deleteMovie?id=${movie.movieId}" class="btn">Xóa</a>
                                </div>
                                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                                    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                                    crossorigin="anonymous"></script>
            </div>
        </div>
        <div style="width: 10%;"></div>
    </div>






    <div class="footer" style=" background-color: #031d2c; color: white;">
        <div class="container"
            style="display: flex; flex-wrap: nowrap; justify-content: space-evenly; ">
            <div class="col">
                <h3 class="footer-title" style="color: white;">Về Chúng Tôi</h3>
                <p style="color: white;">DoubleT Cinema<br>
                    Địa chỉ: DoubleT Cinema, Tp. Đà Nẵng, Việt Nam<br>
                    Điện thoại: 0123 456 789
                </p>
            </div>
            <div class="col">
                <h3 class="footer-title" style="color: white;">Liên Hệ</h3>
                <p style="color: white;">
                    <a href="https://maps.app.goo.gl/uM7ecGRwsK2TKSYG9" target="_blank"
                        style="color: white;text-decoration: none;">
                        DoubleT Cinema, Đà Nẵng
                    </a><br>
                    Email: doubletcinema@gmail.com<br>
                    Website: http://doubleT-cinema.com/
                </p>
            </div>
            <div class="col">
                <a href="" style="color: white;"><img src="" alt="Facebook"></a>
                <a href="" style="color: white;"><img src="" alt="Youtube"></a>
            </div>
        </div>
        <div class="copyright">
            <p style="color: white; ">Copyright &copy; 2024 DoubleT. All Rights Reserved.</p>
        </div>
    </div>
</body>

</html>