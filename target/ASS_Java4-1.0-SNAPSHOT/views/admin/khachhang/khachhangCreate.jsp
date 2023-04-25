<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/23/2023
  Time: 12:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-8 offset-3 mt-3">
    <form method="POST"
          action="/ASS_Java4/kh/store">
        <div class="mb-3">
            <label for="ma" class="form-label">Mã</label>
            <input type="text" class="form-control" id="ma" name="ma" value="${kh.ma}" placeholder="Nhập mã">
            <span style="color: red;"><%=(request.getAttribute("ma") != null) ? request.getAttribute("ma") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="hoTen" class="form-label"> Tên</label>
            <input type="text" class="form-control" id="hoTen" name="ten" value="${kh.ten}"  placeholder="Nhập tên">
            <span style="color: red;"><%=(request.getAttribute("ten") != null) ? request.getAttribute("ten") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="ngaySinh" class="form-label">Ngày sinh</label>
            <input type="date" class="form-control" name="ngaySinh"  value="${kh.ngaySinh}" id="ngaySinh">
            <span style="color: red;"><%=(request.getAttribute("ngaysinh") != null) ? request.getAttribute("ngaysinh") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="diaChi" class="form-label">Địa chỉ</label>
            <input type="text" class="form-control" id="diaChi" name="diaChi"  value="${kh.diaChi}" placeholder="Nhập địa chỉ">
            <span style="color: red;"><%=(request.getAttribute("dc") != null) ? request.getAttribute("dc") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="dienThoai" class="form-label">Điện thoại</label>
            <input type="number" class="form-control" id="dienThoai" name="sdt" value="${kh.sdt}"  placeholder="Nhập số điện thoại">
            <span style="color: red;"><%=(request.getAttribute("dt") != null) ? request.getAttribute("dt") : ""%></span>
            <span style="color: red;"><%=(request.getAttribute("sdt") != null) ? request.getAttribute("sdt") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="thanhpho" class="form-label">Thành phố</label>
            <input type="text" class="form-control" id="thanhpho"  name="thanhPho" value="${kh.thanhPho}" placeholder="Nhập tên thành phố">
        </div>
        <div class="mb-3">
            <label for="tenquocgia" class="form-label">Quốc gia</label>
            <input type="text" class="form-control" id="tenquocgia" name="quocGia" value="${kh.quocGia}" placeholder="Nhập tên quốc gia">
        </div>
        <div class="mb-3">
            <label for="mk" class="form-label">Mật khẩu</label>
            <input type="password" class="form-control" name="matKhau" id="mk" value="${kh.matKhau}" >
        </div>
        <%--kh.qg == "vi ?"selected" : ""--%>
        <button type="submit" class="btn btn-primary mt-3">Thêm mới</button>
    </form>
</div>
