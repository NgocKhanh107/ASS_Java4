package controllers;

import Utils.validate;
import entities.CuaHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.cuahangRepo;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/ch/index",
                "/ch/show",
                "/ch/create",
                "/ch/edit",
                "/ch/update",
                "/ch/delete",
                "/ch/store" }
)
public class cuahangServlet extends HttpServlet {
    cuahangRepo chrepo ;
    public cuahangServlet(){
        chrepo = new cuahangRepo();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            this.index(request, response);
        }else if (uri.contains("create")) {
            this.create(request, response);
        }else if (uri.contains("edit")) {
            this.edit(request, response);
        }else if(uri.contains("delete")) {
            this.delete(request, response);
        }
        else {
            this.index(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idch = request.getParameter("id");
        try {

            this.chrepo.remove(idch);
            response.sendRedirect("/ASS_Java4/ch/index");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro delete");
            this.index(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String idch = request.getParameter("id");
            CuaHang cuahang = chrepo.findById(idch);;
            request.setAttribute("ch",cuahang);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("view", "/views/admin/cuahang/cuahangEdit.jsp");
        request.getRequestDispatcher("/views/admin/cuahang/index.jsp").forward(request,response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/admin/cuahang/cuahangCreate.jsp");
        request.getRequestDispatcher("/views/admin/cuahang/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CuaHang> ds = chrepo.getAll();

        System.out.println(ds);
        request.setAttribute("danhsachCH", ds);
        request.setAttribute("view", "/views/admin/cuahang/cuahangShow.jsp");
        request.getRequestDispatcher("/views/admin/cuahang/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        }else if (uri.contains("update")){
            this.update(request, response);
        }else{
            response.sendRedirect("/ASS_Java4/ch/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idch = request.getParameter("id");
        try {
            CuaHang entity = new CuaHang();

            BeanUtils.populate(entity,
                    request.getParameterMap());
            entity.setId(idch);
            this.chrepo.update(entity);
            response.sendRedirect("/ASS_Java4/ch/index");
        } catch (Exception e) {
            System.out.println("Update Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4"
                    + "/ch/edit?id=" + idch);
        }

    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CuaHang entity = new CuaHang();

        try {
            BeanUtils.populate(entity,
                    request.getParameterMap());
            if (validate.isEmpty(entity.getMa())) {
                request.setAttribute("ma", "Mã cửa hàng không được để trống");

            } else if (validate.isEmpty(entity.getTen())) {
                request.setAttribute("ten", "Tên cửa hàng không được để trống");
            }
            else if (validate.isEmpty(entity.getDiachi())) {
                request.setAttribute("dc", "Địa chỉ không được để trống");
            }
            else if (validate.isEmpty(entity.getThanhpho())) {
                request.setAttribute("tp", "Thành phố không được để trống");
            }else {
                String id = UUID.randomUUID().toString();
                entity.setId(id);
                this.chrepo.create(entity);
                response.sendRedirect("/ASS_Java4/ch/index");
                return;
            }
        } catch (Exception e) {
            System.out.println("Thêm Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4/ch/create");
        }
        request.setAttribute("ch",entity);
        request.setAttribute("view", "/views/admin/cuahang/cuahangCreate.jsp");
        request.getRequestDispatcher("/views/admin/cuahang/index.jsp").forward(request, response);
    }
}
