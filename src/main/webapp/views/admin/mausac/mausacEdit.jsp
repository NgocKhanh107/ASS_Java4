<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/16/2023
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-8 offset-2">
    <form method="POST"
          action="/ASS_Java4/ms/update?id=${ms.id}">
        <div class="mb-3">
            <label for="ma" class="form-label">Mã</label>
            <input type="text" class="form-control" id="ma" name="ma" value="${ ms.ma } " readonly>
        </div>
        <div class="mb-3">
            <label for="hoTen" class="form-label"> Tên</label>
            <input type="text" class="form-control" id="hoTen" name="ten" value="${ ms.ten }">
        </div>

        <button type="submit" class="btn btn-primary mt-3">Update</button>
    </form>
</div>
