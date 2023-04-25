<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/19/2023
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>

<div>
    <a class="btn btn-primary mt-3" href="/ASS_Java4/ch/create" role="button">Thêm mới</a>
    <table class="table table-bordered mt-3">
        <thead class="table-primary">
        <th>STT</th>
        <th>Mã </th>
        <th>Tên</th>
        <th>Địa Chỉ</th>
        <th>Thành Phố</th>
        <th>Quốc Gia</th>
        <th colspan="2" class="text-center">Thao tác</th>
        </thead>
        <tbody>
        <c:forEach items="${ danhsachCH }" var="ch" varStatus="index">
            <tr>
                <td>${index.index + 1}</td>
                <td>${ch.ma}</td>
                <td>${ch.ten}</td>
                <td>${ch.diachi}</td>
                <td>${ch.thanhpho}</td>
                <td>${ch.quocgia}</td>
                <td class="text-center">
                    <a class="btn btn-primary"
                       href="/ASS_Java4/ch/edit?id=${ch.id}"
                    >Cập nhật</a>
                </td>
                <td class="text-center">
                    <a class="btn btn-danger"
                       href="/ASS_Java4/ch/delete?id=${ch.id}"
                       onclick="return confirm('Bạn có chắc muốn xoá cửa hàng có mã ${ch.ma} không?')"
                    >Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
