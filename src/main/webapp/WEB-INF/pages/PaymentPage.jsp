<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán</title>
    <jsp:include page="include/css-page.jsp" />
</head>
<style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
}

h1 {
    font-size: 24px;
    margin-bottom: 20px;
}

.container {
    width: 1240px;
    margin: 0 auto;
}
.container h1{
  padding: 15px 50px;
  background-color: black;
  color: white;
  text-align: center;
}
.thanh-toan {
    margin-bottom: 10px;
    display: flex;

}


input[type="radio"] {
    margin-right: 10px;
}

label {
    cursor: pointer;
}

img {
    width: 100px;
    margin-left: 10px;
}

.noi-dung-thanh-toan {
    border: 1px solid #ccc;
    padding: 10px;
    margin-top: 20px;
}

h2 {
    font-size: 18px;
    margin-bottom: 10px;
}

ul {
    list-style: none;
    padding: 0;
}

li {
    margin-bottom: 5px;
}


button:hover {
    background-color: #333;
}

</style>
<body>
  <jsp:include page="include/header.jsp" />
    <jsp:include page="include/header2.jsp" />
    <div class="container" style="margin-top: 50px; ">
        <h1 style=" background-color: #8d0000; color: white;">Phương thức thanh toán</h1>
        <form action="paypal">
            <div class="thanh-toan">
                <input type="radio" id="metiz" name="phuong-thuc" value="metiz" checked>
                <label for="metiz">Thanh toán bằng Paypal</label>

            </div>
            <div class="thanh-toan">
                <input type="radio" id="hello" name="phuong-thuc" value="hello">
                <label for="hello">Thanh toán bằng ví điện tử</label>

            </div>
            <div class="thanh-toan">
                <input type="radio" id="internet-banking" name="phuong-thuc" value="internet-banking">
                <label for="internet-banking">Thanh toán qua Internet Banking/VISA</label>
            </div>
            <div class="noi-dung-thanh-toan">
                <h2 style="margin-left: 10px;">Nội dung thanh toán</h2>
                <ul style="margin-left: 50px;">
                    <li><b>Phim:</b> ${movie.isPresent() ? movie.get().movieName : 'Unknown'}</li>
                    <li><b>Ngày:</b> <fmt:formatDate pattern="dd/MM/yyyy" value="${showTime.get().showDate}" /></li>
                    <li><b>Thời gian:</b> ${showTime.isPresent() ? showTime.get().timeStart : 'Unknown'} - ${showTime.isPresent() ? showTime.get().timeEnd : 'Unknown'}</li>
                    <li><b>Ghế:</b><c:forEach items="${seats}" var="seat">
                                    <c:out value="${seat.getSeatName()}"/>
                                </c:forEach></li>
                    <li><b>Số vé:</b> ${seats.size()}</li>
                    <li><b>Tổng tiền:</b> ${seats.size() * 60000}</li>
                </ul>
            </div>
     <a href="/paypal?total=${seats.size() * 60000}" style="max-width: 150px;
     text-decoration: none;
     background-color: #8d0000;
     color: #fff;
     font-weight: bold;
     font-size: 18px;
     padding: 10px 20px;
     border: none;
     cursor: pointer;
     display: block;
     margin: 0 auto;
     margin-top: 20px;"><center>Thanh toán</center></a>
        </form>
<br>
    </div>
      <jsp:include page="include/footer.jsp" />
</body>

</html>
