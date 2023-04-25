<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/23/2023
  Time: 12:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<div>
    <a class="btn btn-primary mt-3" href="/ASS_Java4/kh/create" role="button">Thêm mới</a>
    <table class="table table-bordered mt-3">
        <thead class="table-primary">
        <th>STT</th>
        <th>Mã</th>
        <th>Họ Tên</th>
        <th>Ngày Sinh</th>
        <th>Địa Chỉ</th>
        <th>Điện thoại</th>
        <th>Quốc gia</th>
        <th>Mật khẩu</th>
        <th colspan="2">Hành động</th>
        </thead>
        <tbody>
        <c:forEach items="${ danhsachKH }" var="kh" varStatus="index">
            <tr>
                <td>${index.index + 1}</td>
                <td>${kh.ma}</td>
                <td>${kh.ten}</td>
                <td>${kh.ngaySinh}</td>
                <td>${kh.diaChi}</td>
                <td>${kh.sdt}</td>
                <td>${kh.quocGia}</td>
                <td>${kh.matKhau}</td>
                <td class="text-center">
                    <a class="btn btn-primary"
                       href="/ASS_Java4/kh/edit?id=${kh.id}"
                    >Cập nhật</a>
                </td>
                <td class="text-center">
                    <a class="btn btn-danger"
                       href="/ASS_Java4/kh/delete?id=${kh.id}"
                       onclick="return confirm('Bạn có chắc muốn xoá khách hàng có mã ${kh.ma} không?')"
                    >Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
