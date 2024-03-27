<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán</title>
    <link rel="stylesheet" href="style.css">
</head>
<style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 20px;
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

button {
    background-color: #000;
    color: #fff;
    font-size: 16px;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    display: block;
    margin: 0 auto;
    margin-top: 20px;
}

button:hover {
    background-color: #333;
}

</style>
<body>
    <div class="container">
        <h1>Phương thức thanh toán</h1>
        <form action="#">
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
                <h2>Nội dung thanh toán</h2>
                <ul>
                    <li>Phim:</li>
                    <li>Ngày:</li>
                    <li>Thời gian:</li>
                    <li>Ghế:</li>
                    <li>Số vé:</li>
                    <li>Tổng tiền:</li>
                </ul>
            </div>
            <button type="submit">Tiếp tục</button>
        </form>
    </div>
</body>
</html>
