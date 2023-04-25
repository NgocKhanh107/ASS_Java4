<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/16/2023
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<div class="container bg-light">
    <!--slide header-->
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="1000">
                <img class="d-block w-100 img-carousel" src="/ASS_Java4/images/banner-01.jpg">
            </div>
            <div class="carousel-item" data-bs-interval="1000">
                <img class="d-block w-100 img-carousel" src="/ASS_Java4/images/banner-02.jpg">
            </div>
            <div class="carousel-item" data-bs-interval="1000">
                <img class="d-block w-100 img-carousel" src="/ASS_Java4/images/banner-03.jpg">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <!--end slide -->

    <!--Sản phẩm bán chạy-->
    <h4 class="text-center mt-4">Sản phẩm bán chạy nhất</h4>
    <div class="row mt-3">
    <c:forEach items="${ danhsachSP }" var="ctsp">
        <div class="col-md-3 col-12"  >

            <div class="products-single">
                <div class="box-img-hover">
                    <img src="/ASS_Java4/images/${ctsp.sanPham.images}" class="img-fluid" alt="Image">
                    <div class="mask-icon">
                        <a class="cart" href="/ASS_Java4/home/detailSP?id=${ctsp.id}">Add to Cart</a>
                    </div>
                </div>
                <div class="text-center mt-4">
                    <h6>${ctsp.sanPham.ten}</h6>
                    <h5>${ctsp.giaBan}</h5>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
    <div class="col-md-12">
        <ul class="pagination justify-content-end"  style="margin:20px 0">
            <c:if test="${tag>1}">
            <li class="page-item"><a class="page-link" href="/ASS_Java4/home/index?index=${tag-1 }">Previous</a></li>
            </c:if >
            <c:forEach begin="1" end="${endpage}" var="i">
                <li class="page-item ${tag == i ? 'active' : '' }"><a class="page-link"  href="/ASS_Java4/home/index?index=${i }">${i}</a></li>
            </c:forEach>
            <c:if test="${tag < endpage}">
            <li class="page-item"><a class="page-link" href="/ASS_Java4/home/index?index=${tag+1}">Next</a></li>
            </c:if >
        </ul>
    </div>

    <!--End sản phẩm bán chạy-->
    <h4 class="text-center mt-4">Sản phẩm bán chạy nhất</h4>
    <div class="row mt-3">
        <c:forEach items="${ danhsachSP }" var="ctsp">
            <div class="col-md-3 col-12"  >

                <div class="products-single">
                    <div class="box-img-hover">
                        <img src="/ASS_Java4/images/${ctsp.sanPham.images}" class="img-fluid" alt="Image">

                    </div>
                    <div class="text-center mt-4">
                        <h6>${ctsp.sanPham.ten}</h6>
                        <h5>${ctsp.giaBan}</h5>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>

    <!--sản phẩm mới nhất-->
    <!--end sản phẩm mới nhất-->
</div>
