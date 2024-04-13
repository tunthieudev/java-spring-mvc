<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
                <meta content="width=device-width, initial-scale=1.0" name="viewport">
                <meta content="" name="keywords">
                <meta content="" name="description">

                <!-- Customized Bootstrap Stylesheet -->
                <link href="/seller/css/style.css" rel="stylesheet">
            </head>

            <body>
                <div class="container">
                    <div class="product-list">
                        <h2>Product List</h2>
                        <div id="product-container" class="product-container">
                            <!-- Danh sách sản phẩm sẽ được hiển thị tại đây -->
                        </div>
                    </div>
                    <div class="cart">
                        <h2>Shopping Cart</h2>
                        <div id="cart" class="cart-items">
                            <!-- Giỏ hàng sẽ được hiển thị tại đây -->
                        </div>
                        <p>Total: <span id="total-price">$0.00</span></p>
                    </div>
                </div>

                <!-- JavaScript Libraries -->
                <script src="/seller/js/app.js"></script>
            </body>

            </html>