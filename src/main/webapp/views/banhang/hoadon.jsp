<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/18/2023
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<div class=" container mt-4" >
    <h4 class="text-center">Hoá đơn</h4>
    <table class="table  text-danger m-0">
        <tr class="text-center">

            <th scope="col">Sản phẩm </th>
            <th scope="col">Số lượng</th>
            <th scope="col">Đơn giá</th>
            <th scope="col">Thành tiền</th>
        </tr>
        <c:forEach items="${ listdshd}" var="hd">
        <tr>
            <td >
                <div class="row">
                    <div class="col-4">
                        <img src="/ASS_Java4/images/${hd.idChiTietSp.sanPham.images}" alt="" width="60px" height="60px">
                    </div>
                    <div class="col-8">
                        <h4>${hd.idChiTietSp.sanPham.ten}</h4>
                    </div>
                </div>
            </td>
            <td class="text-center">${hd.soLuong}</td>
            <td class="text-center">${hd.donGia}</td>
            <td class="text-center">${hd.soLuong * hd.donGia}</td>
        </tr>
        </c:forEach>
    </table>


</div>