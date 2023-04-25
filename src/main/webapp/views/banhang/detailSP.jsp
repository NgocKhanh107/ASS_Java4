<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/16/2023
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class=" container mt-4" >
    <div class="row">

            <div class="col-xl-5 col-lg-5 col-md-6">
                <div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active"> <img class="d-block w-100" src="/ASS_Java4/images/${ctsp.sanPham.images}"> </div>

                    </div>
                </div>
            </div>

        <!--end ảnh sản phẩm-->

        <!--chi tiết sản phẩm-->
        <div class="col-md-6 col-12"  >

            <div class="product-details">
                <h2 class="product-name">product name goes here</h2>
                <div>
                    <h4>Giá</h4>
                    <h3 class="product-price text-danger">${{ctsp.giaBan}} </h3>

                </div>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>

                <form method="POST"
                      action="/ASS_Java4/home/store?id=${ctsp.id}">
                <div >
                    <div>
                        <button class="btn btn-success" type="button" id="minus" onclick='javascript: document.getElementById("sl").value--;' value='-'>−</button>
                        <input type="number" value="0" name="sl"  id="sl" min="0"  style="width: 50px"/>
                        <button class="btn-success" type="button" id="plus" onclick='javascript: document.getElementById("sl").value++;' value='+'>+</button>
                    </div>
                </div>
                    <button type="submit" class="btn btn-success mt-3">Thêm mới</button>
                </form>
<%--                <a class="btn btn-success mt-3"  href="/ASS_PH27550_war_exploded/home/add?ma=${ctsp.ma}" role="button" ><i class="bi bi-cart"></i>Thêm vào giỏ</a>--%>
            </div>
        </div>
        <!--end chi tiết sản phẩm-->
    </div>
</div>
