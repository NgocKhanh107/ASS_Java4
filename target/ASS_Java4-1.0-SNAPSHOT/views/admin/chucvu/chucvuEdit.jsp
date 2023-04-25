<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 3/14/2023
  Time: 12:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-8 offset-2">
    <form method="POST"
          action="/ASS_Java4/cv/update?id=${cv.id}">
        <div class="mb-3">
            <label for="ma" class="form-label">Mã</label>
            <input type="text" class="form-control" id="ma" name="ma" value="${ cv.ma } " readonly>
        </div>
        <div class="mb-3">
            <label for="hoTen" class="form-label"> Tên</label>
            <input type="text" class="form-control" id="hoTen" name="ten" value="${ cv.ten }">
        </div>

        <button type="submit" class="btn btn-primary mt-3">Update</button>
    </form>
</div>