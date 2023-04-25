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

        <th>Mã</th>
        <th>Tên</th>
        <th colspan="2" class="text-center">Thao tác</th>
        </thead>
        <tbody>
        <c:forEach items="${ danhsachCV }" var="cv">
        <tr>
            <td>${cv.ma}</td>
            <td>${cv.ten}</td>
            <td class="text-center">
                <a class="btn btn-primary"
                   href="/ASS_Java4/cv/edit?id=${cv.id}"
                >Cập nhật</a>
            </td>
            <td class="text-center">
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmDeleteModal" data-bs-id="${cv.id}">
                    Xoá sản phẩm
                </button>

            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmDeleteModalLabel">Xác nhận xoá sản phẩm #<span id="productId"></span></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn xoá sản phẩm này?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <a href="/ASS_Java4/cv/delete?id=${cv.id}" class="btn btn-danger" id="deleteButton">Xoá</a>
                </div>
            </div>
        </div>
    </div>

    <script>
        var modal = document.getElementById('confirmDeleteModal');
        var deleteM = document.getElementById('deleteButton');
        deleteM.onclick = function() {
            modal.style.display = "none";
            // Chuyển hướng đến trang xử lý xoá sản phẩm
            window.location.href = deleteM.href;
        }
        // $('#confirmDeleteModal').on('show.bs.modal', function(event) {
        //     var button = $(event.relatedTarget); // Nút được bấm để mở modal
        //     var id = button.data('id'); // Lấy giá trị ID từ thuộc tính data-id
        //     var modal = $(this);
        //     modal.find('#productId').text(id); // Hiển thị giá trị ID vào modal
        //     modal.find('#deleteButton').attr('href', '/ASS_Java4/cv/delete?id=' + id); // Gán đường dẫn xoá sản phẩm với ID tương ứng
        // });
    </script>
</div>
