package controllers;

import Utils.validate;
import entities.ChucVu;
import entities.Nsx;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.nsxRepo;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/nsx/index",
                "/nsx/show",
                "/nsx/create",
                "/nsx/edit",
                "/nsx/update",
                "/nsx/delete",
                "/nsx/store" }
)
public class nsxServlet extends HttpServlet {
    nsxRepo nsxrepo;
    public nsxServlet(){
        nsxrepo =  new nsxRepo();
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

            nsxrepo.remove(idstr);
            response.sendRedirect("/ASS_Java4/nsx/index");
        } catch (Exception e) {
            e.printStackTrace();
            this.index(request,response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idstr = request.getParameter("id");
        try {
            Nsx nsx = nsxrepo.findById(idstr);
            request.setAttribute("nsx",nsx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("view", "/views/admin/nsx/nsxEdit.jsp");
        request.getRequestDispatcher("/views/admin/nsx/index.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/admin/nsx/nsxCreate.jsp");
        request.getRequestDispatcher("/views/admin/nsx/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Nsx> list = nsxrepo.getAll();
        request.setAttribute("danhsachnsx",list);
        request.setAttribute("view", "/views/admin/nsx/nsxShow.jsp");
        request.getRequestDispatcher("/views/admin/nsx/index.jsp").forward(request, response);
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
            response.sendRedirect("/ASS_Java4/nsx/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idnsx = request.getParameter("id");
        try {
            Nsx entity = new Nsx();
            BeanUtils.populate(entity,
                    request.getParameterMap());

            entity.setId(idnsx);
            this.nsxrepo.update(entity);
            response.sendRedirect("/ASS_Java4/nsx/index");
        } catch (Exception e) {
            System.out.println("Update Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4"
                    + "/nsx/edit?id=" + idnsx);
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Nsx entity = new Nsx();
        try {
            BeanUtils.populate(entity,
                    request.getParameterMap());
            if (validate.isEmpty(entity.getMa())) {
                request.setAttribute("ma", "Mã không được để trống");

            }
            else if(validate.isEmpty(entity.getTen())) {
                request.setAttribute("ten", "Tên không được để trống");
            }
            else {
                String id = UUID.randomUUID().toString();
                entity.setId(id);
                this.nsxrepo.create(entity);
                response.sendRedirect("/ASS_Java4/nsx/index");
                return;
            }
        } catch (Exception e) {
            System.out.println("Thêm Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4/nsx/create");
        }
        request.setAttribute("view", "/views/admin/nsx/nsxCreate.jsp");
        request.getRequestDispatcher("/views/admin/nsx/index.jsp").forward(request,response);
    }
}
