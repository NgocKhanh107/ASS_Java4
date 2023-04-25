package controllers;

import Utils.validate;
import entities.DongSp;
import entities.MauSac;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.dspRepo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/dsp/index",
                "/dsp/show",
                "/dsp/create",
                "/dsp/edit",
                "/dsp/update",
                "/dsp/delete",
                "/dsp/store" }
)
public class dspServlet extends HttpServlet {
    dspRepo dsprepo;
    public dspServlet(){
        dsprepo = new dspRepo();
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

            dsprepo.remove(idstr);
            response.sendRedirect("/ASS_Java4/dsp/index");
        } catch (Exception e) {
            e.printStackTrace();
            this.index(request,response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            DongSp dsp = dsprepo.findById(id);
            request.setAttribute("dsp",dsp);
        } catch (Exception e) {
            e.printStackTrace();
            this.index(request,response);
        }
        request.setAttribute("view", "/views/admin/dsp/dspEdit.jsp");
        request.getRequestDispatcher("/views/admin/dsp/index.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/admin/dsp/dspCreate.jsp");
        request.getRequestDispatcher("/views/admin/dsp/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DongSp> list = dsprepo.getAll();
        request.setAttribute("danhsachDSP",list);
        request.setAttribute("view", "/views/admin/dsp/dspShow.jsp");
        request.getRequestDispatcher("/views/admin/dsp/index.jsp").forward(request, response);
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
            response.sendRedirect("/ASS_Java4/dsp/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String iddsp = request.getParameter("id");
        try {
            DongSp entity = new DongSp();
            BeanUtils.populate(entity,
                    request.getParameterMap());

            entity.setId(iddsp);
            this.dsprepo.update(entity);
            response.sendRedirect("/ASS_Java4/dsp/index");
        } catch (Exception e) {
            System.out.println("Update Thất bại");
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4"
                    + "/dsp/edit?id=" + iddsp);
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DongSp dsp = new DongSp();
        try {
            BeanUtils.populate(dsp,
                    request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

            if (validate.isEmpty(dsp.getMa())) {
                request.setAttribute("ma", "Mã không được để trống");

            }
            else if(validate.isEmpty(dsp.getTen())) {
                request.setAttribute("ten", "Tên không được để trống");
            }
            else {
                try {
                    String id = UUID.randomUUID().toString();
                    dsp.setId(id);
                    this.dsprepo.create(dsp);
                    response.sendRedirect("/ASS_Java4/dsp/index");
                    return;

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/ASS_Java4/dsp/create");
                }
            }
        request.setAttribute("dsp",dsp);
        request.setAttribute("view", "/views/admin/dsp/dspCreate.jsp");
        request.getRequestDispatcher("/views/admin/dsp/index.jsp").forward(request,response);
    }
}
