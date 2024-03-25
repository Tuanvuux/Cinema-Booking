<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script>
        function validatePassword() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (password != confirmPassword) {
                alert("Mật khẩu và xác nhận mật khẩu không khớp.");
                return false;
            }
            return true;
        }

        // Hàm chuyển đổi ngày nhập vào từ dạng DD/MM/YYYY sang MM/DD/YYYY (format của JavaScript Date)
        function formatDate(inputDate) {
            return moment(inputDate, 'DD/MM/YYYY').format('MM/DD/YYYY');
        }

        // Hàm thực hiện chuyển đổi ngày khi người dùng rời khỏi trường ngày sinh
        function convertDate() {
            var inputDate = document.getElementById('birthDay').value;
            var formattedDate = formatDate(inputDate);
            document.getElementById('birthDay').value = formattedDate;
        }
    </script>
</head>
<style>
  /* CSS for basic styling */
body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    margin: 0;
    padding: 0;
}

h2 {
    color: #333;
}

form {
    width: 50%;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

label {
    display: block;
    margin-top: 10px;
}

input[type="text"],
input[type="email"],
input[type="password"] {
    width: 100%;
    padding: 5px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

input[type="submit"] {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 10px;
}

</style>

<body>
    <h2>Đăng ký</h2>
    <form action="/register" method="post" onsubmit="return validatePassword()">
        <label for="firstName">Tên:</label><br>
        <input type="text" id="firstName" name="firstName"><br>

        <label for="lastName">Họ:</label><br>
        <input type="text" id="lastName" name="lastName"><br>

        <label for="birthDay">Ngày sinh (DD/MM/YYYY):</label><br>
        <input type="text" id="birthDay" name="birthDay" onchange="convertDate()"><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email"><br>

        <label for="phone">Số điện thoại:</label><br>
        <input type="text" id="phone" name="phone"><br>

        <label for="address">Địa chỉ:</label><br>
        <input type="text" id="address" name="address"><br>

        <label for="password">Mật khẩu:</label><br>
        <input type="password" id="password" name="password"><br>

        <label for="confirmPassword">Xác nhận mật khẩu:</label><br>
        <input type="password" id="confirmPassword" name="confirmPassword"><br><br>

        <input type="submit" value="Đăng ký">
    </form>
</body>
</html>
