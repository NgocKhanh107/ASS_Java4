<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/17/2023
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-8 offset-2">
    <form method="POST"
          action="/ASS_Java4/dsp/store">
        <div class="mb-3">
            <label for="ma" class="form-label">Mã</label>
            <input type="text" class="form-control" id="ma" name="ma" value="${dsp.ma}" placeholder="Nhập mã">
            <span style="color: red;"><%=(request.getAttribute("ma") != null) ? request.getAttribute("ma") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="hoTen" class="form-label"> Tên</label>
            <input type="text" class="form-control" id="hoTen" name="ten" value="${dsp.ten}" placeholder="Nhập  tên">
            <span style="color: red;"><%=(request.getAttribute("ten") != null) ? request.getAttribute("ten") : ""%></span>
        </div>

        <button type="submit" class="btn btn-primary mt-3">Thêm mới</button>
    </form>
</div>
