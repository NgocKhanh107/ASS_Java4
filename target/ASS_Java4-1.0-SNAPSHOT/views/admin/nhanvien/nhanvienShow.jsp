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
<div>
    <a class="btn btn-primary mt-3" href="/ASS_Java4/nv/create" role="button">Thêm mới</a>
    <table class="table table-bordered mt-3">
        <thead class="table-primary">
        <th>STT</th>
        <th>Mã</th>
        <th>Tên</th>
        <th>Tên Đệm</th>
        <th>Họ</th>
        <th>Giới tính</th>
        <th>Ngày sinh</th>
        <th>Địa Chỉ</th>
        <th>SĐT</th>
        <th>Cửa hàng</th>
        <th>Chức vụ</th>
        <th colspan="2" class="text-center">Thao tác</th>
        </thead>
        <tbody>
        <c:forEach items="${ danhsachNV }" var="nv" varStatus="index">
        <tr>
            <td>${index.index + 1}</td>
            <td>${nv.ma}</td>
            <td>${nv.ten}</td>
            <td>${nv.tendem}</td>
            <td>${nv.ho}</td>
            <td>${nv.gioitinh}</td>
            <td>${nv.ngaysinh}</td>
            <td>${nv.diachi}</td>
            <td>${nv.sdt}</td>
            <td>${nv.cuaHang.ten}</td>
            <td>${nv.chucVu.ten}</td>
            <td class="text-center">
                <a class="btn btn-primary"
                   href="/ASS_Java4/nv/edit?id=${nv.id}"
                >Cập nhật</a>
            </td>
            <td class="text-center">
                <a class="btn btn-danger"
                   href="/ASS_Java4/nv/delete?id=${nv.id}"
                >Xóa</a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
