package controllers;

import Utils.validate;
import entities.ChucVu;
import entities.MauSac;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.mausacRepo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/ms/index",
                "/ms/show",
                "/ms/create",
                "/ms/edit",
                "/ms/update",
                "/ms/delete",
                "/ms/store" }
)
public class mausacServlet extends HttpServlet {
    mausacRepo msrepo;
    public mausacServlet(){
        msrepo = new mausacRepo();
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
            msrepo.remove(idstr);
            response.sendRedirect("/ASS_Java4/ms/index");
        } catch (Exception e) {
            e.printStackTrace();
            this.index(request,response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            MauSac ms = msrepo.findById(id);
            request.setAttribute("ms",ms);
        } catch (Exception e) {
            e.printStackTrace();
            this.index(request,response);
        }
        request.setAttribute("view", "/views/admin/mausac/mausacEdit.jsp");
        request.getRequestDispatcher("/views/admin/mausac/index.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/admin/mausac/mausacCreate.jsp");
        request.getRequestDispatcher("/views/admin/mausac/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MauSac> list = msrepo.getAll();
        request.setAttribute("danhsachMS",list);
        request.setAttribute("view", "/views/admin/mausac/mausacShow.jsp");
        request.getRequestDispatcher("/views/admin/mausac/index.jsp").forward(request, response);
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
            response.sendRedirect("/ASS_Java4/ms/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idms = request.getParameter("id");
        try {
            MauSac entity = new MauSac();
            BeanUtils.populate(entity,
                    request.getParameterMap());

            entity.setId(idms);
            this.msrepo.update(entity);
            response.sendRedirect("/ASS_Java4/ms/index");
        } catch (Exception e) {
            System.out.println("Update Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4"
                    + "/ms/edit?id=" + idms);
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MauSac ms = new MauSac();


            try {
                BeanUtils.populate(ms,
                        request.getParameterMap());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (validate.isEmpty(ms.getMa())) {
                request.setAttribute("ma", "Mã không được để trống");

            }
            else if(validate.isEmpty(ms.getTen())) {
                request.setAttribute("ten", "Tên không được để trống");
            }
            else {
                try {
                    String id = UUID.randomUUID().toString();
                    ms.setId(id);
                    this.msrepo.create(ms);
                    response.sendRedirect("/ASS_Java4/ms/index");
                    return;

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/ASS_Java4/ms/create");
                }
            }
        request.setAttribute("ms",ms);
        request.setAttribute("view", "/views/admin/mausac/mausacCreate.jsp");
        request.getRequestDispatcher("/views/admin/mausac/index.jsp").forward(request,response);
    }
}
