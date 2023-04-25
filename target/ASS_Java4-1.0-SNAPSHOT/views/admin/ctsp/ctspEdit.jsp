<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/19/2023
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>

<div class="col-8 offset-3 mt-3">
    <form method="POST"
          action="/ASS_Java4/ctsp/update?id=${ctsp.id}">
        <div class="mb-3">
            <label for="nambh" class="form-label"> Năm BH</label>
            <input type="number" class="form-control" id="nambh" name="namBh" value="${ctsp.namBh}">
        </div>
        <div class="mb-3">
            <label for="moTa" class="form-label">Mô tả</label>
            <input type="text" class="form-control" id="moTa" name="moTa" value="${ctsp.moTa}">
        </div>
        <div class="mb-3">
            <label  class="form-label"> SL tồn</label>
            <input type="number" class="form-control"  name="soLuongTon" value="${ctsp.soLuongTon}">
        </div>
        <div class="mb-3">
            <label  class="form-label"> Giá nhập</label>
            <input type="number" class="form-control"  name="giaNhap" value="${ctsp.giaNhap}">
        </div>
        <div class="mb-3">
            <label  class="form-label"> Giá bán</label>
            <input type="number" class="form-control"  name="giaBan" value="${ctsp.giaBan}">
        </div>

        <div class="mb-3">
            <label  class="form-label">màu sắc</label>
            <select name="mauSacID" class="form-select">
                <c:forEach var="ms" items="${listms}">
                    <option value="${ms.id}" ${ctsp.mauSac.id == ms.id ? "selected" : ""}>${ms.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">ĐSP</label>
            <select name="dspID" class="form-select">
                <c:forEach var="dsp" items="${listdsp}">
                    <option value="${dsp.id}" ${ctsp.dongSp.id == dsp.id ? "selected" : ""}>${dsp.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">NSX</label>
            <select name="nsxID" class="form-select">
                <c:forEach var="nsx" items="${listnsx}">
                    <option value="${nsx.id}" ${ctsp.nsx.id == nsx.id ? "selected" : ""}>${nsx.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">Sản phẩm</label>
            <select name="sanPhamID" class="form-select">
                <c:forEach var="sp" items="${listsp}">
                    <option value="${sp.id}" ${ctsp.sanPham.id == sp.id ? "selected" : ""}>${sp.ten}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Update</button>
    </form>
</div>
