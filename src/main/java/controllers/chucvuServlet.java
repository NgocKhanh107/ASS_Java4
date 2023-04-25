package controllers;

import Utils.validate;
import entities.ChucVu;
import entities.CuaHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.chucvuRepo;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/cv/index",
                "/cv/show",
                "/cv/create",
                "/cv/edit",
                "/cv/update",
                "/cv/delete",
                "/cv/store" }
)
public class chucvuServlet extends HttpServlet {
    chucvuRepo cvrepo ;

    public chucvuServlet () {
        this.cvrepo = new chucvuRepo();
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
            ChucVu cv = cvrepo.findById(idstr);
            cvrepo.remove(cv,idstr);
            response.sendRedirect("/ASS_Java4/cv/index");
        } catch (Exception e) {
            e.printStackTrace();
            this.index(request,response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idstr = request.getParameter("id");
        try {
            ChucVu cv = cvrepo.findById(idstr);
            request.setAttribute("cv",cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("view", "/views/admin/chucvu/chucvuEdit.jsp");
        request.getRequestDispatcher("/views/admin/chucvu/index.jsp").forward(request, response);

    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/admin/chucvu/chucvuCreate.jsp");
        request.getRequestDispatcher("/views/admin/chucvu/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ChucVu> listcv = cvrepo.getAll();
        request.setAttribute("danhsachCV",listcv);
        request.setAttribute("view", "/views/admin/chucvu/chucvuShow.jsp");
        request.getRequestDispatcher("/views/admin/chucvu/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String idcv = request.getParameter("id");
        try {
            ChucVu entity = new ChucVu();
            BeanUtils.populate(entity,
                    request.getParameterMap());

            entity.setId(idcv);
            this.cvrepo.update(entity);
            response.sendRedirect("/ASS_Java4/cv/index");
        } catch (Exception e) {
            System.out.println("Update Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4"
                    + "/cv/edit?id=" + idcv);
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ChucVu entity = new ChucVu();
        try {
            BeanUtils.populate(entity,
                    request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

            if (validate.isEmpty(entity.getTen())) {
                request.setAttribute("error", "Tên không được để trống");

            }
            else if(validate.isEmpty(entity.getMa())) {
                request.setAttribute("ma", "Mã không được để trống");
            }else {
                try {
                    String id = UUID.randomUUID().toString();
                    entity.setId(id);
                    this.cvrepo.create(entity);
                    response.sendRedirect("/ASS_Java4/cv/index");
                    return;
                } catch (Exception e) {
                    System.out.println("Thêm Thất bại");
                    e.printStackTrace();
                    response.sendRedirect("/ASS_Java4/cv/create");
                }
            }
        request.setAttribute("cv",entity);
        request.setAttribute("view", "/views/admin/chucvu/chucvuCreate.jsp");
        request.getRequestDispatcher("/views/admin/chucvu/index.jsp").forward(request,response);

    }
}
