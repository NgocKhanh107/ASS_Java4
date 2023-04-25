package controllers;

import Utils.validate;
import entities.ChucVu;
import entities.SanPham;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.sanphamRepo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
@MultipartConfig
@WebServlet(
        { 	    "/sp/index",
                "/sp/show",
                "/sp/create",
                "/sp/edit",
                "/sp/update",
                "/sp/delete",
                "/sp/store" }
)
public class sanphamServlet extends HttpServlet {
    sanphamRepo sprepo ;
    public sanphamServlet(){
        sprepo = new sanphamRepo();
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
        try {
            String idstr = request.getParameter("id");
            SanPham sp = sprepo.findById(idstr);
            sprepo.remove(idstr);
            response.sendRedirect("/ASS_Java4/sp/index");
        } catch (Exception e) {
            e.printStackTrace();
            this.index(request,response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idsp = request.getParameter("id");
        try {
            SanPham sp = sprepo.findById(idsp);
            request.setAttribute("sp",sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("view", "/views/admin/sanpham/sanphamEdit.jsp");
        request.getRequestDispatcher("/views/admin/sanpham/index.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/admin/sanpham/sanphamCreate.jsp");
        request.getRequestDispatcher("/views/admin/sanpham/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPham> ds = sprepo.getAll();
        request.setAttribute("danhsachSP",ds);
        request.setAttribute("view", "/views/admin/sanpham/sanphamShow.jsp");
        request.getRequestDispatcher("/views/admin/sanpham/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        }else if (uri.contains("update")){
            this.update(request, response);
        }else{
            response.sendRedirect("/ASS_Java4/cv/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idsp = request.getParameter("id");
        try {
            SanPham entity = new SanPham();
            BeanUtils.populate(entity,
                    request.getParameterMap());

            entity.setId(idsp);
            this.sprepo.update(entity);
            response.sendRedirect("/ASS_Java4/sp/index");
        } catch (Exception e) {
            System.out.println("Update Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4"
                    + "/sp/edit?id=" + idsp);
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SanPham sp = new SanPham();
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        Part filePart = request.getPart("images");
        String fileName = filePart.getSubmittedFileName();
        try {
            if (validate.isEmpty(ma)) {
                request.setAttribute("ma", "Mã không được để trống");

            } else if (validate.isEmpty(ten)) {
                request.setAttribute("ten", "Tên không được để trống");
            } else {
                String id = UUID.randomUUID().toString();
                sp.setId(id);
                sp.setMa(ma);
                sp.setTen(ten);
                sp.setImages(fileName);
                this.sprepo.create(sp);
                response.sendRedirect("/ASS_Java4/sp/index");
                return;
            }
            }  catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("/ASS_Java4/sp/create");
            }
        request.setAttribute("view", "/views/admin/sanpham/sanphamCreate.jsp");
        request.getRequestDispatcher("/views/admin/sanpham/index.jsp").forward(request, response);
    }
}
