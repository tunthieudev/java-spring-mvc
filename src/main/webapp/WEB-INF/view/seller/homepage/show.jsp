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
                <div class="header">
                    <p class="logo">LOGO</p>
                    <div class="cart"><i class="fa-solid fa-cart-shopping"></i>
                        <p id="count">0</p>
                    </div>
                </div>
                <div class="container">
                    <div id="root"></div>
                    <div class="sidebar">
                        <div class="head">
                            <p>My Cart</p>
                        </div>
                        <div id="cartItem">Your cart is empty</div>
                        <div class="foot">
                            <h3>Total</h3>
                            <h2 id="total">$ 0.00</h2>
                        </div>
                    </div>
                </div>
                <!-- JavaScript Libraries -->
                <script src="/seller/js/app.js"></script>
            </body>

            </html>