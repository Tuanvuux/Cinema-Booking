<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chọn Chỗ Xem Phim</title>
    <jsp:include page="../include/css-page.jsp" />
    <style>
        .seat {
            width: 30px;
            height: 30px;
            margin: 5px;
            border: 1px solid #ccc;
            display: inline-block;
            cursor: pointer;
        }
        .selected {
            background-color: #ff0000;
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
    <h2>Chọn Chỗ Xem Phim</h2>
    <div>
        <c:forEach items="${seatList}" var="seat" varStatus="loop">
            <c:if test="${loop.index % 10 == 0}">
                <div style="clear:both;"></div>
            </c:if>
            <c:choose>
                <c:when test="${seat.status eq '1'}">
                    <div class="seat selected" id="seat_${seat.seatId}" onclick="toggleSeat('seat_${seat.seatId}', '${seat.seatId}', '${seat.seatName}')">${seat.seatName}</div>
                </c:when>
                <c:when test="${seat.status eq '2'}">
                    <div class="seat booked" id="seat_${seat.seatId}">${seat.seatName}</div>
                </c:when>
                <c:otherwise>
                    <div class="seat" id="seat_${seat.seatId}" onclick="toggleSeat('seat_${seat.seatId}', '${seat.seatId}', '${seat.seatName}')">${seat.seatName}</div>
                </c:otherwise>
            </c:choose>
        </c:forEach>

    </div>
    <div id="selectedSeats">
        <%-- Hiển thị danh sách các ghế đã chọn --%>
        <c:forEach items="${selectedSeats}" var="selectedSeatId">
            <div onclick="toggleSeat('seat_${selectedSeatId}', '${selectedSeatId}')">Ghế đã chọn: ${selectedSeatId}</div>
        </c:forEach>
    </div>
    <a href="/payment" class="btn" style="background-color: #007bff; color: #fff; padding: 10px 20px; border-radius: 5px; text-decoration: none;">Thanh toán</a>

    <script>
function toggleSeat(seatId, seatIdValue, seatName) {
    var seatElement = document.getElementById(seatId);
    var isSelected = seatElement.classList.contains('selected');
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/selectSeat", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.status === 'success') {
                if (isSelected) {
                    seatElement.classList.remove('selected');
                } else {
                    seatElement.classList.add('selected');
                }
            } else {
                console.log("Lỗi khi cập nhật trạng thái ghế.");
            }
        }
    };
    // Gửi dữ liệu dưới dạng form data
    var data = "seatId=" + encodeURIComponent(seatIdValue);
    xhr.send(data);
}

       </script>

</body>
</html>
