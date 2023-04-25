<%@ page import="entities.ChucVu" %>
<%@ page import="entities.NhanVien" %>
<%@ page import="repositories.impl.chucvuRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
<div>
<%--    <span style="color: red;">Bạn có muốn xoá chức vụ có mã ${cv.ma},${ sessionScope.loi }</span>--%>
    <a class="btn btn-primary mt-3" href="/ASS_Java4/cv/create" role="button">Thêm mới</a>
    <table class="table table-bordered mt-3">
        <thead class="table-primary">
        <th>STT</th>
        <th>Mã</th>
        <th>Tên</th>
        <th colspan="2" class="text-center">Thao tác</th>
        </thead>
        <tbody>
        <c:forEach items="${ danhsachCV }" var="cv" varStatus="index">
        <tr>
            <td>${index.index + 1}</td>
            <td>${cv.ma}</td>
            <td>${cv.ten}</td>
            <td class="text-center">
                <a class="btn btn-primary"
                   href="/ASS_Java4/cv/edit?id=${cv.id}"
                >Cập nhật</a>
            </td>
            <td class="text-center">
                <a class="btn btn-danger"
                   href="/ASS_Java4/cv/delete?id=${cv.id}"
                   onclick="return confirm(
                       'Bạn có chắc muốn xoá chức vụ có mã ${cv.ma}, ${ sessionScope.loi }'
                           )"
                >Xóa</a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
