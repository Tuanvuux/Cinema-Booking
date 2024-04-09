<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chọn Chỗ Xem Phim</title>
    <jsp:include page="include/css-page.jsp" />
    <style>
        .seat {
            width: 30px;
            height: 30px;
            margin: 5px;
            border: 1px solid gray;
            display: inline-block;
            cursor: pointer;
            color: gray;
            font-size: 10px;
            padding-top: 6px;
            font-weight: bold;
        }
        .seat:hover{
            background-color: rgb(10, 203, 10);
            color: white;
        }
        .selected {
            background-color: rgb(10, 203, 10);
            color: white;
            border: none;
        }
        .booked {
            background-color: #cccccc;
        }
        #selectedSeats {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="include/header.jsp" />
    <jsp:include page="include/header2.jsp" />
    <div class="film" style="margin: 20px 50px 30px 50px;">
        <div style="width: 10%;"></div>
        <h5 style="color: #8d0000;  margin-left: 170px;">Chọn Chỗ Xem Phim</h5>
        <br>
        <center>
            <div>
                <div class="screen" style="width: 500px;background-color: #8d0000;">
                    <p style="color: white;">MÀN HÌNH</p>
                </div>
                <br>
                <div>
                    <c:forEach items="${seatList}" var="seat" varStatus="loop">
                        <c:if test="${loop.index % 10 == 0}">
                            <div style="clear:both;"></div>
                        </c:if>
                        <c:choose>
                            <c:when test="${seat.status eq '1'}">
                                <div class="seat selected" id="seat_${seat.seatId}"
                                    onclick="toggleSeat('seat_${seat.seatId}', '${seat.seatId}', '${seat.seatName}')">
                                    ${seat.seatName}</div>
                            </c:when>
                            <c:when test="${seat.status eq '2'}">
                                <div class="seat booked" id="seat_${seat.seatId}">${seat.seatName}</div>
                            </c:when>
                            <c:otherwise>
                                <div class="seat" id="seat_${seat.seatId}"
                                    onclick="toggleSeat('seat_${seat.seatId}', '${seat.seatId}', '${seat.seatName}')">
                                    ${seat.seatName}</div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </div>
            </div>
        </center>
        <div id="selectedSeats" style="display: flex;">
            <div style="display: flex; width: 50%; margin-left: 170px;">
                <h5 style="color: #8d0000">Ghế đã chọn:</h5>
                <div id="selectedSeatsList" style="margin-left: 20px"></div>
            </div>
            <a href="/payment" class="btn"
                style="background-color: #8d0000; color: #fff; padding: 10px 20px; border-radius: 5px; text-decoration: none; float: right; margin-right:170px;">
                Thanh toán
            </a>
        </div>

    </div>
    <div style="width: 10%;"></div>
    </div>
    <jsp:include page="include/footer.jsp" />
    <script>

        function toggleSeat(seatId, seatIdValue, seatName) {
            var seatElement = document.getElementById(seatId);
            var isSelected = seatElement.classList.contains('selected');
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/selectSeat", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        var response = JSON.parse(xhr.responseText);
                        if (response.status === 'success') {
                            if (isSelected) {
                                seatElement.classList.remove('selected');
                                // Xóa ghế đã chọn khỏi danh sách
                                removeSelectedSeat(seatName); // Thay seatIdValue thành seatName
                            } else {
                                seatElement.classList.add('selected');
                                // Thêm ghế đã chọn vào danh sách
                                addSelectedSeat(seatName); // Truyền seatName vào hàm
                            }
                        } else {
                            console.log("Lỗi khi cập nhật trạng thái ghế.");
                        }
                    } else {
                        console.log("Lỗi khi gửi yêu cầu đến máy chủ.");
                    }
                }
            };
            // Gửi dữ liệu dưới dạng form data
            var data = "seatId=" + encodeURIComponent(seatIdValue);
            xhr.send(data);
        }

        // Hàm thêm ghế đã chọn vào danh sách
        function addSelectedSeat(seatName) {
            var selectedSeatsList = document.getElementById('selectedSeatsList');
            var existingSeats = selectedSeatsList.textContent;

            // Thêm dấu phẩy nếu danh sách không trống
            if (existingSeats !== "") {
                existingSeats += ", ";
            }

            // Thêm tên ghế mới vào danh sách
            selectedSeatsList.textContent = existingSeats + seatName;
        }

        // Hàm xóa ghế đã chọn khỏi danh sách
        function removeSelectedSeat(seatName) {
            var selectedSeatsList = document.getElementById('selectedSeatsList');
            var existingSeats = selectedSeatsList.textContent;

            // Tìm vị trí của tên ghế trong danh sách
            var index = existingSeats.indexOf(seatName);
            if (index !== -1) {
                // Xóa tên ghế khỏi danh sách
                var newSeats = existingSeats.slice(0, index) + existingSeats.slice(index + seatName.length); // Không cần thêm +2 vì không có dấu phẩy và khoảng trắng sau tên ghế
                selectedSeatsList.textContent = newSeats;
            }
        }

    </script>

</body>
</html>