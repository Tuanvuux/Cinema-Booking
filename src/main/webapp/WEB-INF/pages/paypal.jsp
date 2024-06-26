<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PayPal Checkout</title>
    <!-- Nhúng JavaScript SDK của PayPal -->
    <script src="https://www.paypal.com/sdk/js?client-id=AWea2gnSojv3kkNOXdVWGzblzmDjdD-cRt3j1nsKaQ-EN8KRaEERM1xeIeWKxslRFpzaGEz98I2OFhAM&currency=USD"></script>
</head>
<body>


<center>
    <div style="margin-top: 200px;">
        <label for="amount">Số tiền cần thanh toán:</label>
        <input class="form-control" type="text" id="amount" placeholder="Nhập số tiền"
        style="padding: 10px; border: 1px solid #ccc; border-radius: 5px; margin-bottom: 10px;" value="${total}" readonly>

            <!-- Nút thanh toán PayPal sẽ được render tại đây -->
            <div id="paypal-button-container" style="margin-left: 400px;"></div>
    </div>
</center>

    <script>
        paypal.Buttons({
            createOrder: function(data, actions) {
                var amount = document.getElementById('amount').value;
                return actions.order.create({
                    purchase_units: [{
                        amount: {
                            value: amount,
                            currency_code: 'USD'
                        }
                    }]
                });
            },
            onApprove: function(data, actions) {
                return actions.order.capture().then(function(details) {
                    var fullResponse = {
                        orderID: data.orderID,
                        payerID: data.payerID,
                        details: details
                    };
                    window.location.href = "/paymentsuccess";
                });
            },
            onError: function(err) {
                // Nếu có lỗi, chuyển hướng sang trang error.html
                window.location.href = "/paymenerror";
            }
        }).render('#paypal-button-container');
    </script>

</body>
</html>