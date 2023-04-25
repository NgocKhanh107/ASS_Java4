<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/19/2023
  Time: 9:38 PM
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
          action="/ASS_Java4/ch/update?id=${ch.id}">
        <div class="mb-3">
            <label for="ma" class="form-label">Mã<span class="rq">*</span></label>
            <input type="text" class="form-control" id="ma" name="ma" value="${ch.ma}" readonly>
        </div>
        <div class="mb-3">
            <label for="hoTen" class="form-label"> Tên<span class="rq">*</span></label>
            <input type="text" class="form-control" id="hoTen" name="ten" value="${ch.ten}">
        </div>
        <div class="mb-3">
            <label for="diaChi" class="form-label">Địa chỉ<span class="rq">*</span></label>
            <input type="text" class="form-control" id="diaChi" name="diachi" value="${ch.diachi}">
        </div>

        <div class="mb-3">
            <label for="thanhpho" class="form-label">Thành phố<span class="rq">*</span></label>
            <input type="text" class="form-control" id="thanhpho"  name="thanhpho" value="${ ch.thanhpho }">
        </div>
        <div class="mb-3">
        <label for="tenquocgia" class="form-label">Quốc gia</label>
        <input type="text" class="form-control" id="tenquocgia" name="quocgia" value="${ ch.quocgia }">
        </div>


        <button type="submit" class="btn btn-primary mt-3">Update</button>
    </form>
</div>
