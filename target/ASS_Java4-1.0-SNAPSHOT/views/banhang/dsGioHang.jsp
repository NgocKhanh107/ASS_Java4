<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/17/2023
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<div class=" container mt-4" >
    <h4 class="text-center">Giỏ hàng</h4>
    <form method="POST"
          action="/ASS_Java4/home/hoadon">
        <div class="bg-danger"><%=(request.getAttribute("soluong") != null) ? request.getAttribute("soluong") : ""%></div>
    <table class="table  text-danger m-0">
        <tr class="text-center">
            <th scope="col">
                <input type="checkbox" name="sanpham">
            </th>
<%--            <th scope="col "> </th>--%>
            <th scope="col ">Sản phẩm </th>
            <th scope="col">Số lượng</th>
            <th scope="col">Đơn giá</th>
            <th scope="col">Thành tiền</th>
            <th scope="col">Thao tác</th>

        </tr>
    <c:forEach items="${ listdsgiohang }" var="gh">
        <tr >
            <td class="text-center">

                <input type="checkbox" name="sanpham" value="${gh.idChiTietSp.id},${gh.idGioHang.id}">
            </td>
            <td >
                <div class="row">
                    <div class="col-4">
                        <img src="/ASS_Java4/images/${gh.idChiTietSp.sanPham.images}" alt="" width="60px" height="60px">
                    </div>
                    <div class="col-8 ">
                        <h4>${gh.idChiTietSp.sanPham.ten}</h4>
                    </div>
                </div>
            </td>
            <td>
                <div>
<%--                    <button class="btn btn-success" type="button" id="minus" onclick='javascript: document.getElementById("sl").value--;' value='-'>−</button>--%>
                    <a class="btn btn-success" type="button" href="/ASS_Java4/home/themxoa?action=dec&idctsp=${gh.idChiTietSp.id}&idgh=${gh.idGioHang.id}"><i class="bi bi-dash"></i></a>
                    <input type="text"  value="${gh.soLuong}" min="0" id="sl" name="sl"  style="width: 50px" readonly/>
                     <a class="btn btn-success" type="button" href="/ASS_Java4/home/themxoa?action=inc&idctsp=${gh.idChiTietSp.id}&idgh=${gh.idGioHang.id}"><i class="bi bi-plus"></i></a>
<%--                    <button class="btn-success" type="button" id="plus" onclick='javascript: document.getElementById("sl").value++;' value='+'>+</button>--%>
                </div>
            </td>
            <td class="text-center">${gh.idChiTietSp.giaBan}</td>
            <td class="text-center">${gh.soLuong * gh.idChiTietSp.giaBan}</td>

            <td class="text-center">
<%--                <button type="button" class=" btn btn-danger">Xoá</button>--%>
                <a class="btn btn-danger" type="button" href="/ASS_Java4/home/delete?idctsp=${gh.idChiTietSp.id}&idgh=${gh.idGioHang.id}">Xoá</a>
            </td>

        </tr>
    </c:forEach>
    </table>

      <button type="submit" class="btn btn-success mt-3">Add to cart</button>
    </form>
</div>
<%--<script>--%>
<%--    $(document).ready(function() {--%>
<%--        $('input[type=checkbox]').change(function() {--%>
<%--            var total = 0;--%>
<%--            $('input[type=checkbox]:checked').each(function() {--%>

<%--            });--%>
<%--            $('#total').text(total);--%>
<%--            $.ajax({--%>
<%--                url: '/ASS_Java4/home/hoadon',--%>
<%--                type: 'POST',--%>
<%--                data: $('form').serialize(),--%>
<%--                success: function(data) {--%>
<%--                    console.log(data);--%>
<%--                }--%>
<%--            });--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
