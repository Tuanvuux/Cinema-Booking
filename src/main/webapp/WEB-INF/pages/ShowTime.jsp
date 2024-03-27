<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    </style>
</head>
<body>

    <h2>Chọn ngày chiếu</h2>
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
                        <button class="day-button" name="selectedDate" value="${date}" id="btn-${loop.index}" ${loop.index == 0 ? 'class="selected"' : ''}>
                            ${date.split(' ')[0]}/${date.split(' ')[1]}
                        </button>
                    </th>
                </c:forEach>
            </tr>
        </tbody>
    </table>
 <table>
     <thead>
         <tr>
             <th>Movie ID</th>
             <th>Movie Name</th>
             <!-- Add more headers as needed -->
         </tr>
     </thead>
     <tbody id="showtimeTableBody">
         <!-- Movie data will be inserted here -->
     </tbody>
 </table>



    <script>
        // Lấy tất cả các nút
        const buttons = document.querySelectorAll('.day-button');

        // Lặp qua từng nút để thêm xử lý sự kiện click
        buttons.forEach(button => {
            button.addEventListener('click', function() {
                // Xóa tất cả các lớp CSS 'selected' trên tất cả các nút
                buttons.forEach(btn => {
                    btn.classList.remove('selected');
                });

                // Thêm lớp CSS 'selected' cho nút được chọn
                this.classList.add('selected');

                // Gửi dữ liệu ngày được chọn đến controller
                const selectedDate = this.value;
                sendSelectedDate(selectedDate);
            });
        });

        function sendSelectedDate(selectedDate) {
            const xhr = new XMLHttpRequest();
            xhr.open('POST', '/processSelectedDate');
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
            xhr.onload = function() {
                if (xhr.status === 200) {
                   console.log('Đã gửi ngày được chọn đến controller');
                                  console.log(xhr.responseText);

                                  // Parse JSON response
                                  const movies = JSON.parse(xhr.responseText);

                                  // Update HTML content with movie data
                                  updateMovieTable(movies);
                } else {
                    console.error('Đã xảy ra lỗi khi gửi dữ liệu đến controller');
                }
            };
            // Chuyển đổi ngày thành định dạng "yyyy-MM-dd" và gửi dưới dạng JSON
            xhr.send(JSON.stringify({ selectedDate: selectedDate }));
        }


 function updateMovieTable(movies) {
        const showtimeTableBody = document.getElementById('showtimeTableBody');
        showtimeTableBody.innerHTML = ''; // Clear existing content

        // Add new movie data
        movies.forEach(function(movie) {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${movie.movieId}</td>
                <td>${movie.movieName}</td>
                <!-- Add more columns as needed -->
            `;
            showtimeTableBody.appendChild(row);
        });
    }
    </script>

    <script>
        // Tự động chọn nút của ngày hiện tại khi trang được tải
        window.onload = function() {
            document.getElementById('btn-0').click();
        };
    </script>
</body>
</html>
