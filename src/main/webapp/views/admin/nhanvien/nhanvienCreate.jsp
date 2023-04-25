<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/13/2023
  Time: 11:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>

<div class="col-8 offset-3 mt-3">
    <form method="POST"
          action="/ASS_Java4/nv/store">
        <div class="mb-3">
            <label for="ma" class="form-label">Mã</label>
            <input type="text" class="form-control" id="ma" name="ma" value="${nv.ma}" placeholder="Nhập mã">
            <span style="color: red;"><%=(request.getAttribute("ma") != null) ? request.getAttribute("ma") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="ten" class="form-label"> Tên</label>
            <input type="text" class="form-control" id="ten" name="ten" value="${nv.ten}" placeholder="Nhập họ tên">
            <span style="color: red;"><%=(request.getAttribute("ten") != null) ? request.getAttribute("ten") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="tendem" class="form-label">Tên đệm</label>
            <input type="text" class="form-control" id="tendem" name="tendem" placeholder="Nhập tên đệm">
        </div>
        <div class="mb-3">
            <label for="ho" class="form-label">Họ</label>
            <input type="text" class="form-control" id="ho" name="ho" value="${nv.ho}" placeholder="Nhập họ ">
            <span style="color: red;"><%=(request.getAttribute("ho") != null) ? request.getAttribute("ho") : ""%></span>
        </div>
        <div class="mb-3 row">
            <label  class="form-label">Giới tính</label>
            <div>
                <input class="form-check-input" type="radio" name="gioitinh" id="nam" value="Nam" checked>
                <label class="form-check-label" for="nam">
                    Nam
                </label>
                <input class="form-check-input" type="radio" name="gioitinh" id="nu" value="Nữ">
                <label class="form-check-label" for="nu">
                   Nữ
                </label>
        </div>
            <span style="color: red;"><%=(request.getAttribute("gioitinh") != null) ? request.getAttribute("gioitinh") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="ngaySinh" class="form-label">Ngày sinh</label>
            <input type="date" class="form-control" name="ngaysinh" id="ngaySinh"  value="${nv.ngaysinh}">
            <span style="color: red;"><%=(request.getAttribute("ngaysinh") != null) ? request.getAttribute("ngaysinh") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="diaChi" class="form-label">Địa chỉ</label>
            <input type="text" class="form-control" id="diaChi" name="diachi" value="${nv.diachi}" placeholder="Nhập địa chỉ" >
            <span style="color: red;"><%=(request.getAttribute("dc") != null) ? request.getAttribute("dc") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="dienThoai" class="form-label">Điện thoại</label>
            <input type="number" class="form-control" id="dienThoai" name="sdt" value="${nv.sdt}" placeholder="Nhập số điện thoại">
            <span style="color: red;"><%=(request.getAttribute("dt") != null) ? request.getAttribute("dt") : ""%></span>
            <span style="color: red;"><%=(request.getAttribute("sdt") != null) ? request.getAttribute("sdt") : ""%></span>
        </div>

        <div class="mb-3">
            <label for="mk" class="form-label">Mật khẩu</label>
            <input type="password" class="form-control" id="mk" name="matkhau" placeholder="Nhập mật khẩu">
        </div>
        <div class="mb-3">
            <label  class="form-label">Cửa hàng</label>
            <select name="cuaHangID" class="form-select">
                <c:forEach var="ch" items="${listch}">
                    <option value="${ch.id}">${ch.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">Chức vụ</label>
            <select name="chucVuID" class="form-select">
                <c:forEach var="cv" items="${listcv}">
                    <option value="${cv.id}">${cv.ten}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Thêm mới</button>
    </form>
</div>

