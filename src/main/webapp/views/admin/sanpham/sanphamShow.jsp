<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/14/2023
  Time: 12:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<div>
    <a class="btn btn-primary mt-3" href="/ASS_Java4/sp/create" role="button">Thêm mới</a>
    <table class="table table-bordered mt-3">
        <thead class="table-primary">
        <th>STT</th>
        <th>Mã</th>
        <th>Tên</th>
        <th colspan="2" class="text-center">Thao tác</th>
        </thead>
        <tbody>
        <c:forEach items="${ danhsachSP}" var="sp" varStatus="index">
        <tr>
            <td>${index.index + 1}</td>
            <td>${ sp.ma }</td>
            <td>${ sp.ten }</td>

            <td class="text-center">
                <a class="btn btn-primary"
                   href="/ASS_Java4/sp/edit?id=${sp.id}"
                >Cập nhật</a>
            </td>
            <td class="text-center">
                <a class="btn btn-danger"
                   href="/ASS_Java4/sp/delete?id=${sp.id}"
                   onclick="return confirm('Bạn có chắc muốn xoá chức vụ có mã ${sp.ma} không?')"
                >Xóa</a>
            </td>
        </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

