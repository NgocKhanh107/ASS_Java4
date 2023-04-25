<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/17/2023
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>

<div class="col-8 offset-3 mt-3">
    <form method="POST"
          action="/ASS_Java4/ctsp/store">
        <div class="mb-3">
            <label for="nambh" class="form-label"> Năm BH</label>
            <input type="number" class="form-control" id="nambh" name="namBh" value="${ctsp.namBh}"  placeholder="Nhập năm bán hàng">
            <span style="color: red;"><%=(request.getAttribute("nambh") != null) ? request.getAttribute("nambh") : ""%></span>
            <span style="color: red;"><%=(request.getAttribute("namso") != null) ? request.getAttribute("namso") : ""%></span>
        </div>
        <div class="mb-3">
            <label for="moTa" class="form-label">Mô tả</label>
            <input type="text" class="form-control" id="moTa" name="moTa" placeholder="Nhập mô tả">

        </div>
        <div class="mb-3">
            <label  class="form-label"> SL tồn</label>
            <input type="number" class="form-control"  name="soLuongTon"  placeholder="Nhập số lượng tồn">
        </div>
        <div class="mb-3">
            <label  class="form-label"> Giá nhập</label>
            <input type="number" class="form-control"  name="giaNhap" value="${ctsp.giaNhap}"  placeholder="Nhập giá nhập">
            <span style="color: red;"><%=(request.getAttribute("gianhap") != null) ? request.getAttribute("gianhap") : ""%></span>
            <span style="color: red;"><%=(request.getAttribute("ktgianhap") != null) ? request.getAttribute("ktgianhap") : ""%></span>
        </div>
        <div class="mb-3">
            <label  class="form-label"> Giá bán</label>
            <input type="number" class="form-control"  name="giaBan"  value="${ctsp.giaBan}" placeholder="Nhập giá bán">
            <span style="color: red;"><%=(request.getAttribute("giaban") != null) ? request.getAttribute("giaban") : ""%></span>
            <span style="color: red;"><%=(request.getAttribute("gia") != null) ? request.getAttribute("gia") : ""%></span>
        </div>

        <div class="mb-3">
            <label  class="form-label">Màu Sắc</label>
            <select name="mauSacID" class="form-select">
                <c:forEach var="ms" items="${listms}">
                    <option value="${ms.id}">${ms.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">ĐSP</label>
            <select name="dspID" class="form-select">
                <c:forEach var="dsp" items="${listdsp}">
                    <option value="${dsp.id}">${dsp.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">NSX</label>
            <select name="nsxID" class="form-select">
                <c:forEach var="nsx" items="${listnsx}">
                    <option value="${nsx.id}">${nsx.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">Sản phẩm</label>
            <select name="sanPhamID" class="form-select">
                <c:forEach var="sp" items="${listsp}">
                    <option value="${sp.id}">${sp.ten}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Thêm mới</button>
    </form>
</div>

