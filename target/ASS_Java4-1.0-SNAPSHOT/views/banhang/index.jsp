<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/13/2023
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/ASS_Java4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link type="text/css" rel="stylesheet" href="/ASS_Java4/css/lap1.css"/>
    <link type="text/css" rel="stylesheet" href="/ASS_Java4/css/style.css"/>
    <%--    <link type="text/css" rel="stylesheet" href="/ASS_PH27550_war_exploded/css/test.css" />--%>
    <link type="text/css" rel="stylesheet" href="/ASS_Java4/css/testsp.css"/>
    <link type="text/css" rel="stylesheet" href="/ASS_Java4/css/testGiohang.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <header>
        <!-- container -->
        <div class=" mt-4 bg-light">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <img class="col-md-3" src="/ASS_Java4/images/logoinis.png" alt="">
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-5 mt-4">
                    <div class="col-md-6  input-group mb-3">
                        <input class="form-control me-2 rounded-pill" type="search" placeholder="Search"
                               aria-label="Search">
                        <button class="btn btn-success" type="button">
                            <i class="bi bi-search"></i>
                        </button>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-4 mt-4 ">
                    <ul class="nav justify-content-end">
                        <c:if test="${sessionScope.kh == null }">
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="/ASS_Java4/login">Sign In </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="/ASS_Java4/register">Sign Up</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.kh != null }">
                            <li class="nav-item mt-2">
<%--                                <a href="#muahang" class="text-dark mt-2">--%>
<%--                                    <i class="bi bi-cart-dash"></i>--%>
<%--                                </a>--%>
<%--                                <span--%>
<%--                                        class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">--%>
<%--                                       --%>
<%--                                </span>--%>
                                    <button type="button" class="btn btn-link position-relative">
                                        <a href="/ASS_Java4/home/dsGioHang" class="text-dark"><i class="bi bi-cart-dash"></i></a>
                                        <span
                                                class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
<%--                                                ${cart_list.size()}--%>
                                             ${sessionScope.total} ${sl}
                                        </span>
                                    </button>
                            </li>
                            <li class="nav-item dropdown ms-3">
                                <a class="nav-link dropdown-toggle text-dark" href="#" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    Xin chào, ${sessionScope.kh.ho} ${sessionScope.kh.tenDem} ${sessionScope.kh.ten}
                                </a>
                                <ul class="dropdown-menu dropdown-menu-success">
                                    <li><a class="dropdown-item text-dark" href="/ASS_Java4/home/dshoadon">Đơn mua</a></li>
                                    <li><a class="dropdown-item text-dark" href="/ASS_Java4/logout">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>

            <nav id="navigation" class="navbar navbar-expand-lg bg-light">
                <!-- container -->
                <div class="container-fluid">
                    <!-- responsive-nav -->
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#responsive-nav" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div id="responsive-nav" class="collapse navbar-collapse">
                        <!-- NAV -->
                        <ul class="main-nav navbar-nav me-auto mb-2 mb-lg-0 ">
                            <li class="nav-item">
                                <a class="nav-link active text-dark" aria-current="page" href="/ASS_Java4/home/index">Trang chủ</a>
                            </li>
                            <li>
                                <a class="nav-link text-dark" href="/ASS_PH27550_war_exploded/ch/index">Giới thiệu</a>
                            </li>
                            <li>
                                <a class="nav-link text-dark" href="/ASS_PH27550_war_exploded/sp/index">Liên hệ</a>
                            </li>
                            <li>
                                <a class="nav-link text-dark" href="/ASS_PH27550_war_exploded/nv/index">Hỏi đáp</a>
                            </li>
                        </ul>

                        <!-- /NAV -->
                    </div>
                    <!-- /responsive-nav -->
                </div>
                <!-- /container -->
            </nav>
        </div>
    </header>
    <div class="body">
        <jsp:include page="${view}"></jsp:include>
    </div>
    <footer class="bg-dark">
        <div class="row mt-2 ms-5">
            <div class="col-md-3">
                <img src="/ASS_Java4/images/logoinis.png" alt="" class="col-md-10">
                <div class="footer-widget ">
                    <ul>
                        <li><a href="#"><i class="bi bi-facebook"></i></a></li>
                        <li><a href="#"><i class="bi bi-youtube"></i></a></li>
                        <li><a href="#"><i class="bi bi-tiktok"></i></a></li>
                        <li><a href="#"><i class="bi bi-wikipedia"></i></a></li>
                    </ul>

                </div>
            </div>
            <div class="col-md-6"></div>
            <div class="col-md-3 ">
                <p class="text-light ml-0 mt-4">ⓒ 2020 innisfree Inc. <br>All rights reserved.</p>
            </div>
        </div>
        <div class="row mt-2 ms-5 mb-5">
            <a class="col-4 text-light" href="#">Chính sách bảo hành</a>
            <a class="col-4 text-light" href="#">Chính sách bảo mật</a>
            <a class="col-4 text-light" href="#">Chính sách mua hàng</a>
        </div>

    </footer>
</div>
<script src="ASS_Java4/js/bootstrap.js/bootstrap.min.js"></script>
<script src="ASS_Java4/js/jquery.min.js"></script>
<script src="ASS_Java4/js/popper.js"></script>
</body>
</html>
