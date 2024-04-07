<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Time</title>

    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        .day-button {
            background-color: #f0f0f0;
            border: 1px solid #ccc;
            padding: 5px 10px;
            cursor: pointer;
        }
        .day-button:hover {
            background-color: #ddd;
        }
        .selected {
            background-color: #007bff; /* Màu nền của nút khi được chọn */
            color: #fff; /* Màu chữ của nút khi được chọn */
        }
         .day-button.selected {
                background-color: #007bff; /* Màu nền của nút khi được chọn */
                color: #fff; /* Màu chữ của nút khi được chọn */
            }
    </style>
</head>
<body>
 <jsp:include page="include/header.jsp" />
    <jsp:include page="include/header2.jsp" />

<h2>Chọn ngày chiếu</h2>
<form id="dateForm" action="/booking?movieId=${movie.movieId}" method="post"> <!-- Địa chỉ endpoint của controller xử lý POST -->
    <table>
        <thead>
            <tr>
                <c:forEach var="day" items="${daysOfWeek}">
                    <td>${day}</td>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach var="date" items="${dates}" varStatus="loop">
                    <th>
                        <button type="submit" class="day-button ${loop.index == 0 ? 'selected' : ''}" name="selectedDate" value="${date}">
                            ${date.split(' ')[0]}/${date.split(' ')[1]}
                        </button>
                    </th>
                </c:forEach>
            </tr>
        </tbody>
    </table>
</form>
<h2>Chọn lịch chiếu</h2>
<h1>Phim ${movie.movieName}</h1>

<table>
    <thead>
        <tr>
            <th>Thời gian chiếu</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="showtime" items="${showTimes}">
            <c:forEach var="roomShowTime" items="${roomShowTimes}">
                <tr>
                    <td>
                        <c:set var="formattedStartTime">
                            <fmt:formatDate value="${showtime.timeStart}" pattern="HH:mm" />
                        </c:set>
                        <c:set var="formattedEndTime">
                            <fmt:formatDate value="${showtime.timeEnd}" pattern="HH:mm" />
                        </c:set>
                        ${formattedStartTime} - ${formattedEndTime}
                        <br/>
                        Phòng ${roomShowTime.room.roomId}
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
    </tbody>
</table>
<script>
  document.addEventListener("DOMContentLoaded", function() {
      // Lấy tất cả các nút ngày
      const buttons = document.querySelectorAll('.day-button');

      // Tìm xem có nút nào đã được chọn mặc định hay không
      let isSelected = false;
      buttons.forEach(button => {
          if (button.classList.contains('selected')) {
              isSelected = true;
          }
      });

      // Nếu không có nút nào được chọn mặc định, chọn nút đầu tiên
      if (!isSelected) {
          buttons[0].classList.add('selected');
      }

      // Lặp qua từng nút để thêm sự kiện click
      buttons.forEach(button => {
          button.addEventListener('click', function() {
              // Loại bỏ lớp 'selected' từ tất cả các nút
              buttons.forEach(btn => {
                  btn.classList.remove('selected');
              });

              // Thêm lớp 'selected' cho nút được nhấp vào
              this.classList.add('selected');
          });
      });
  });
</script>

    <jsp:include page="include/footer.jsp" />
</body>
</html>
