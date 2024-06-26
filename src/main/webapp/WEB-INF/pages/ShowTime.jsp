<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Time</title>
    <jsp:include page="include/css-page.jsp" />
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: center;
        }
        .day-button, .showtime-button {
            background-color: #f0f0f0;
            border: 1px solid #ccc;
            padding: 5px 10px;
            cursor: pointer;
        }
        .day-button:hover, .showtime-button:hover {
            background-color: #8d0000;
            color: white;
        }
        .day-button.selected {
            background-color: #8d0000; /* Thay 'blue' bằng màu bạn muốn */
            color: white; /* Thêm màu chữ tương thích */
        }
        /* Định dạng cho thẻ a trong thẻ td */
        td a.movie-link {
        width: 120px;
            display: block; /* Hiển thị thẻ a như là khối để chiếm toàn bộ chiều rộng của thẻ td */
            padding: 5px; /* Khoảng cách giữa các nội dung trong thẻ a */
            border: 1px solid #ccc; /* Đường viền của thẻ a */
            text-align: center; /* Căn giữa nội dung của thẻ a */
            background-color: #f9f9f9; /* Màu nền của thẻ a */
            color: black; /* Màu chữ */
            text-decoration: none; /* Loại bỏ gạch chân mặc định */
        }

        td a.movie-link:hover {
            background-color: #8d0000;
                        color: white;
        }


    </style>
</head>
<body>
    <jsp:include page="include/header.jsp" />
    <jsp:include page="include/header2.jsp" />

    <div class="ngaychieu" style="display: flex; flex-direction: column; margin: 20px 120px 50px 100px;">
            <h2 style="color: #8d0000;">Chọn ngày chiếu</h2>
            <div class="ngay" style="display: flex; justify-content: center;">
                <form id="dateForm" action="/showtime" method="post">
                    <!-- Địa chỉ endpoint của controller xử lý POST -->
                    <table>
                        <thead>
                            <tr>
                                <c:forEach var="day" items="${daysOfWeek}">
                                    <td style="border: 1px solid black;">${day}</td>
                                </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:forEach var="date" items="${dates}" varStatus="loop">
                                    <th style="border: 1px solid black;">
                                        <button type="submit" class="day-button" name="selectedDate" value="${date}"
                                            onclick="toggleButtonColor(this)">
                                            ${date.split(' ')[0]}/${date.split(' ')[1]}
                                        </button>
                                    </th>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        <h2 style="margin-bottom:20px; color: #8d0000;">Chọn lịch chiếu</h2>
<c:forEach items="${movies}" var="movie">
    <div class="lichchieu" style="display: flex; margin: 20px 120px 50px 100px;">
        <div class="left" style="display: flex; flex-direction: column; width: 20%; margin-bottom:20px;">
            <div class="phim">
                <h5 style="margin-bottom:20px; font-weight: bold;">Phim ${movie.movieName}</h5>
                <img src="movie/getPhoto/<c:out value='${movie.movieId}'/>" style="width: 220px; height: 270px;">
            </div>
        </div>

        <div class="right" style="display: flex; justify-content: center; width: 70%;">
            <table>
                <tr>
                    <c:forEach items="${movieShowtime}" var="movieShowtime">
                        <c:forEach items="${showTimes}" var="showTime">
                            <c:forEach items="${roomShowTimes}" var="roomShowTime">
                                <c:if test="${movieShowtime.movie.movieId eq movie.movieId and movieShowtime.showTime.showTimeId eq showTime.showTimeId and roomShowTime.showTime.showTimeId eq showTime.showTimeId}">
                                    <c:set var="startTime" value="${showTime.timeStart}" />
                                    <c:set var="endTime" value="${showTime.timeEnd}" />
                                    <td>
                                        <a href="/seat?roomId=${roomShowTime.room.roomId}&movieId=${movie.movieId}&showTimeId=${showTime.showTimeId}" class="movie-link">
                                            ${fn:substring(startTime, 0, 5)} - ${fn:substring(endTime, 0, 5)}<br>${roomShowTime.room.roomId}
                                        </a>
                                    </td>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>
</c:forEach>


    <jsp:include page="include/footer.jsp" />
</body>
<script>
    function toggleButtonColor(button) {
        // Loại bỏ lớp 'selected' khỏi tất cả các nút
        var buttons = document.querySelectorAll('.day-button');
        buttons.forEach(function(btn) {
            btn.classList.remove('selected');
        });

        // Thêm lớp 'selected' cho nút được nhấn
        button.classList.add('selected');
    }
</script>

</html>
