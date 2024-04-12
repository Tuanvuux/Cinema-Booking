<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Success</title>
    <jsp:include page="include/css-page.jsp" />
</head>
<body style="background-color: #f2f5fb">
    <jsp:include page="include/header.jsp" />
    <jsp:include page="include/header2.jsp" />
    <img src="../../resources/img/cinema/cbo.png" style="width: 100%; height: 400px;">
    <div class="film" style=" display: flex;flex-wrap: wrap; height: 300px;">

        <div style="width: 10%;"></div>

        <div class="main-home" style="width: 80%; margin-left: 100px; ">

        <br>
            <h3>Bạn đã thanh toán thành công!</h3>
            <p>Cảm ơn bạn đã lựa chọn DoubleT. Chúc bạn có những phút giây xem phim vui vẻ!</p>
            <br>
        </div>
        <div style="width: 10%;"></div>
    </div>



    <jsp:include page="include/footer.jsp" />
</body>
</html>