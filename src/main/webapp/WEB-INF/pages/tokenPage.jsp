<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enter Token</title>
    <style>
        body {
            background-color: #f2f5fb;
            display: flex;
            justify-content: center;
            margin-top: 200px;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            background-color: #031d2c;
            border-radius: 15px;
            width: 700px;
            height: 300px;
            color: white;
        }

        button {
            border: none;
            background-color: #f2f5fb;
            height: 21px;
        }
        button:hover{
            background-color: #8d0000;
            color: white;
        }
    </style>
</head>

<body>
    <div class="container">
        <img src="../../resources/img/cinema/logo.png" style="width: 100px; margin-bottom: 50px;">
        <form action="/verify-token" method="post">
            <input type="hidden" name="email" value="${param.email}">
            <label for="token" style="font-size: large; font-weight: 500;">Mã xác thực: </label>
            <input type="text" id="token" name="token" required>
            <button type="submit">Gửi</button>
            <p style="color: gray;">Nhập mã xác thực để kích hoạt tài khoản của bạn. Lưu ý, mã chỉ có hiệu lực trong vòng 12 giờ.</p>
        </form>
    </div>
</body>

</html>