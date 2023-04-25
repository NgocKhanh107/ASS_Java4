<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/17/2023
  Time: 2:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<div>
    <a class="btn btn-primary mt-3" href="/ASS_Java4/ctsp/create" role="button">Thêm mới</a>
    <table class="table table-bordered mt-3">
        <thead class="table-primary">
        <th>STT</th>
        <th>Năm</th>
        <th>Mô tả</th>
        <th>SL tồn</th>
        <th>Giá nhập</th>
        <th>Giá bán</th>
        <th>Màu sắc</th>
        <th>DSP</th>
        <th>Sản phẩm</th>
        <th>NSX</th>
        <th colspan="2" class="text-center">Thao tác</th>
        </thead>
        <tbody>
        <c:forEach items="${ danhsachCTSP }" var="ctsp" varStatus="index">
            <tr>
                <td>${index.index + 1}</td>
                <td>${ctsp.namBh}</td>
                <td>${ctsp.moTa}</td>
                <td>${ctsp.soLuongTon}</td>
                <td>${ctsp.giaNhap}</td>
                <td>${ctsp.giaBan}</td>
                <td>${ctsp.mauSac.ten}</td>
                <td>${ctsp.dongSp.ten}</td>
                <td>${ctsp.nsx.ten}</td>
                <td>${ctsp.sanPham.ten}</td>
                <td class="text-center">
                    <a class="btn btn-primary"
                       href="/ASS_Java4/ctsp/edit?id=${ctsp.id}"
                    >Cập nhật</a>
                </td>
                <td class="text-center">
                    <a class="btn btn-danger"
                       href="/ASS_Java4/ctsp/delete?id=${ctsp.id}"
                       onclick="return confirm('Bạn có chắc muốn xoá ctsp  không?')"
                    >Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="col-md-12">
        <ul class="pagination justify-content-end"  style="margin:20px 0">
            <c:if test="${tag>1}">
                <li class="page-item"><a class="page-link" href="/ASS_Java4/ctsp/index?index=${tag-1 }">Previous</a></li>
            </c:if >
            <c:forEach begin="1" end="${endpage}" var="i">
                <li class="page-item ${tag == i ? 'active' : '' }"><a class="page-link"  href="/ASS_Java4/ctsp/index?index=${i }">${i}</a></li>
            </c:forEach>
            <c:if test="${tag < endpage}">
                <li class="page-item"><a class="page-link" href="/ASS_Java4/ctsp/index?index=${tag+1}">Next</a></li>
            </c:if >
        </ul>
    </div>
</div>>
