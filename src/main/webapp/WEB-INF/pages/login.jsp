<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            margin: 0;
            padding: 0;
        }
        h2 {
            color: #333;
        }
        form {
            margin: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #fff;
            border-radius: 5px;
            max-width: 300px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="email"],
        input[type="password"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        a {
            display: inline-block;
            margin-left: 10px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>
<body>
    <h2>Đăng nhập</h2>
    <form action="/login" method="post">
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email"><br>

        <label for="password">Mật khẩu:</label><br>
        <input type="password" id="password" name="password"><br><br>

        <input type="submit" value="Đăng nhập">
        <a href="/register">Đăng ký</a>
    </form>
</body>
</html>
