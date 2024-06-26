<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8" />
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="G1 - Dự án laptopshop" />
                    <meta name="author" content="G1" />
                    <title>Dashboard - G1</title>
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

                </head>

                <body class="sb-nav-fixed">
                    <jsp:include page="../layout/header.jsp" />
                    <div id="layoutSidenav">
                        <jsp:include page="../layout/sidebar.jsp" />
                        <div id="layoutSidenav_content">
                            <main>
                                <div class="container-fluid px-40 sty">
                                    <h1 class="mt-4">Manage Statistics</h1>
                                    <ol class="breadcrumb mb-4">
                                        <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Product</li>
                                    </ol>

                                    <div class="container">
                                        <div class="row justify-content-between align-items-center">
                                            <div class="col-md-4">
                                                <form class="input-group">
                                                    <input type="text" class="form-control"
                                                        placeholder="Nhập nội dung cần tìm..." name="keyword"
                                                        value="${keyword}">
                                                    </input>
                                                    <div class="input-group-append">
                                                        <button class="btn btn-primary" type="submit">Tìm
                                                            kiếm</button>
                                                    </div>
                                            </div>

                                            <div class="col-md-4">
                                                <span style="font-weight: bold; font-size: 20px;">
                                                    Tổng doanh thu:
                                                    <fmt:formatNumber type="number" value="${totalAllRevenue}" /> đ
                                                </span>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="dropdown float-right">
                                                    <button class="btn btn-success dropdown-toggle" type="button"
                                                        id="filterDropdown" data-toggle="dropdown" aria-haspopup="true"
                                                        aria-expanded="false" style="width: 100px;">
                                                        Lọc
                                                    </button>
                                                    <div class="dropdown-menu dropdown-menu-right"
                                                        aria-labelledby="filterDropdown" style="width: 200px;">
                                                        <a class="dropdown-item"
                                                            href="/admin/statistic?sortOrder=asc">Doanh Thu
                                                            Tăng Dần</a>
                                                        <a class="dropdown-item"
                                                            href="/admin/statistic?sortOrder=desc">Doanh Thu
                                                            Giảm Dần</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <form action="/admin/statistic" method="get" class="form-inline">
                                            <div class="form-group mx-sm-3 mb-2">
                                                <label for="startDate">Ngày bắt đầu</label>
                                                <input type="date" class="form-control" id="startDate" name="startDate"
                                                    placeholder="Start Date" required>
                                            </div>
                                            <div class="form-group mx-sm-3 mb-2">
                                                <label for="endDate">Ngày kết thúc</label>
                                                <input type="date" class="form-control" id="endDate" name="endDate"
                                                    placeholder="End Date" required>
                                            </div>
                                            <button type="submit" class="mx-sm-3 btn btn-primary mb-2">Tìm
                                                kiếm</button>
                                        </form>
                                    </div>

                                    <div class="mt-5">
                                        <div class="row">
                                            <div class="col-12 mx-auto">
                                                <table class="table table-bordered table-hover"
                                                    style="text-align: center;">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">No</th>
                                                            <th scope="col">Image</th>
                                                            <th scope="col">Name</th>
                                                            <th scope="col">Description</th>
                                                            <th scope="col">Sold</th>
                                                            <th scope="col">Revenue</th>
                                                            <th scope="col">Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="product" items="${products}" varStatus="loop">
                                                            <tr>
                                                                <th scope="row">${loop.index+1}</th>

                                                                <td>
                                                                    <img src="/images/product/${product.image}"
                                                                        class="img-fluid rounded-circle"
                                                                        style="width: 60px; height: 60px;" alt="">
                                                                </td>
                                                                <td>${product.name}</td>
                                                                <td>${product.shortDesc}</td>
                                                                <td>
                                                                    ${listAmountSold[loop.index]}
                                                                </td>
                                                                <td>
                                                                    <fmt:formatNumber type="number"
                                                                        value="${listRevenue[loop.index]}" /> đ
                                                                </td>
                                                                <td><a href="/admin/statistic/${product.id}">Xem chi
                                                                        tiết</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </main>
                            <jsp:include page="../layout/footer.jsp" />
                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                        crossorigin="anonymous"></script>
                    <script src="js/scripts.js"></script>
                    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
                </body>

                </html>