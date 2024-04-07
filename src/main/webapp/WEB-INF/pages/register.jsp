<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <style>
        body {
            background-color: #f2f5fb;
        }

        .border-form {
            margin: 80px auto;
            background-color: #03141c;
            color: #fff;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            width: 600px;
            border-radius: 10px;
            padding-top: 20px;
            padding-bottom: 30px;
        }

        .border-form .img-tab {
            text-align: center;
        }

        .border-form p {
            font-weight: 500;
            font-size: 35px;
            margin: 5px;
            text-align: center;
            margin: 0;
            margin-top: 10px;
        }

        label {
            font-size: 16px;
            font-weight: 600;
            margin-left: 5px;
        }

        form {
            margin: 20px 60px;
            width: 700px;
        }

        .Hoten,
        .ngaysdt,
        .matkhau {
            display: flex;
            width: 450px;
        }

        .mat,
        .khau,
        .ngay,
        .sdt,
        .Ho,
        .Ten {
            width: 50%;
        }

        .mat,
        .ngay,
        .Ten {
            margin-right: 40px;
        }

        input[type="text"],
        input[type="email"],
        input[type="date"],
        input[type="password"] {
            color: #000;
            margin: 5px 0 10px 0;
            padding: 10px 15px;
            border-radius: 7px;
            font-size: 15px;
            border: 1.5px solid rgb(199, 196, 196);
            width: 450px;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            background-color: #fff;
            font-weight: 600;
        }

        #firstName,
        #lastName,
        #phone,
        #birthDay,
        #password,
        #confirmPassword {
            width: 100%;
        }

        input[type="text"]:hover,
        input[type="email"]:hover,
        input[type="date"]:hover,
        input[type="password"]:hover {
            border: 2px solid gray;
        }

        input[type="submit"] {
            padding: 10px;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            font-weight: 600;
            font-size: 18px;
            width: 480px;
            border-radius: 7px;
            border: none;
            background-color: #f2f5fb;
            color: #031d2c;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            border: 2px solid rgb(199, 196, 196);
            background-color: #03141c;
            color: #fff;
        }

        /* CSS for basic styling */
    </style>
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
    </script>
</head>
<body>
    <div class="border-form">
    <div class="img-tab">
         <img src="../../resources/img/cinema/logo.png" alt="" height="150px" width="150px" style="border-radius: 50%;">
    </div>
    <p>Đăng ký</p>
    <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validatePassword()">
                <div class="Hoten">
                    <div class="Ten">
                        <label for="firstName">Tên:</label><br>
                        <input type="text" id="firstName" name="firstName" required><br>
                    </div>
                    <div class="Ho">
                        <label for="lastName">Họ:</label><br>
                        <input type="text" id="lastName" name="lastName" required><br>
                    </div>
                </div>
                <div class="ngaysdt">
                    <div class="ngay">
                        <label for="birthDay">Ngày sinh: </label><br>
                        <input type="date" id="birthDay" name="birthDay" required><br>
                    </div>
                    <div class="sdt">
                        <label for="phone">Số điện thoại:</label><br>
                        <input type="text" id="phone" name="phone" required><br>
                    </div>
                </div>
                <label for="email">Email:</label><br>
                <input type="email" id="email" name="email" required><br>

                <label for="address">Địa chỉ:</label><br>
                <input type="text" id="address" name="address" required><br>
                <div class="matkhau">
                    <div class="mat">
                        <label for="password">Mật khẩu:</label><br>
                        <input type="password" id="password" name="password" placeholder="Mật khẩu" required><br>
                    </div>
                    <div class="khau">
                        <label for="confirmPassword">Xác nhận mật khẩu:</label><br>
                        <input type="password" id="confirmPassword" name="confirmPassword"
                            placeholder="Nhập lại mật khẩu" required><br>
                    </div>

                </div>
                <input type="submit" value="Đăng ký">
            </form>

    <c:if test="${param.error != null}">
        <p style="color: red;">Registration failed! Please try again.</p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </c:if>
    </div>
</body>
</html>
