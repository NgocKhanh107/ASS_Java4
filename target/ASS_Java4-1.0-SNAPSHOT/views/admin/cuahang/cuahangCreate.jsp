<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/19/2023
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .rq {
        color: red;
    }
</style>
<div class="col-8 offset-3 mt-3">
    <form method="POST"
          action="/ASS_Java4/ch/store">
        <div class="mb-3">
            <label for="ma" class="form-label">Mã<span class="rq">*</span></label>
            <input type="text" class="form-control" id="ma" name="ma" value="${ch.ma}" placeholder="Nhập mã">
            <span style="color: red;"><%=(request.getAttribute("ma") != null) ? request.getAttribute("ma") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="hoTen" class="form-label"> Tên<span class="rq">*</span></label>
            <input type="text" class="form-control" id="hoTen" name="ten" value="${ch.ten}" placeholder="Nhập tên">
            <span style="color: red;"><%=(request.getAttribute("ten") != null) ? request.getAttribute("ten") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="diaChi" class="form-label">Địa chỉ<span class="rq">*</span></label>
            <input type="text" class="form-control" id="diaChi" name="diachi" value="${ch.diachi}" placeholder="Nhập địa chỉ">
            <span style="color: red;"><%=(request.getAttribute("dc") != null) ? request.getAttribute("dc") : ""%></span>
        </div>

        <div class="mb-3">
            <label for="thanhpho" class="form-label">Thành phố<span class="rq">*</span></label>
            <input type="text" class="form-control" id="thanhpho"  name="thanhpho" value="${ch.thanhpho}" placeholder="Nhập tên thành phố">
            <span style="color: red;"><%=(request.getAttribute("tp") != null) ? request.getAttribute("tp") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="tenquocgia" class="form-label">Quốc gia</label>
            <input type="text" class="form-control" id="tenquocgia" name="quocgia" placeholder="Nhập tên quốc gia">
        </div>


        <button type="submit" class="btn btn-primary mt-3">Thêm mới</button>
    </form>
</div>
