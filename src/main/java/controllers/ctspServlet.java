package controllers;

import Utils.validate;
import entities.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.*;
import viewmodel.chitietSPViewModel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/ctsp/index",
                "/ctsp/show",
                "/ctsp/create",
                "/ctsp/edit",
                "/ctsp/update",
                "/ctsp/delete",
                "/ctsp/store" }
)
public class ctspServlet extends HttpServlet {
    mausacRepo msrepo = new mausacRepo();
    sanphamRepo sprepo = new sanphamRepo();
    nsxRepo nsxrepo = new nsxRepo();
    dspRepo dsprepo = new dspRepo();
    chitietSpRepo ctsprepo ;
    public ctspServlet(){
        ctsprepo = new chitietSpRepo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            this.index(request, response);
        }else if (uri.contains("create")) {
            this.show(request, response);
        }else if (uri.contains("edit")) {
            this.edit(request, response);
        }else if (uri.contains("delete")) {
            this.delete(request, response);
        }else {
            this.index(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            String id = request.getParameter("id");
            ChiTietSp ctsp = ctsprepo.findById(id);
            ctsprepo.delete(ctsp);
            response.sendRedirect("/ASS_Java4/ctsp/index");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro delete");
            this.index(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Nsx> listnsx = nsxrepo.getAll();
        List<DongSp> listdsp =dsprepo.getAll();
        List<SanPham> listsp = sprepo.getAll();
        List<MauSac> listms =  msrepo.getAll();
        request.setAttribute("listms",listms);
        request.setAttribute("listnsx",listnsx);
        request.setAttribute("listdsp",listdsp);
        request.setAttribute("listsp",listsp);

        String id = request.getParameter("id");
        ChiTietSp ctsp = ctsprepo.findById(id);
        request.setAttribute("ctsp",ctsp);
        request.setAttribute("view", "/views/admin/ctsp/ctspEdit.jsp");
        request.getRequestDispatcher("/views/admin/ctsp/index.jsp").forward(request,response);

    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Nsx> listnsx = nsxrepo.getAll();
        List<DongSp> listdsp =dsprepo.getAll();
        List<SanPham> listsp = sprepo.getAll();
        List<MauSac> listms =  msrepo.getAll();
        request.setAttribute("listms",listms);
        request.setAttribute("listnsx",listnsx);
        request.setAttribute("listdsp",listdsp);
        request.setAttribute("listsp",listsp);
        request.setAttribute("view", "/views/admin/ctsp/ctspCreate.jsp");
        request.getRequestDispatcher("/views/admin/ctsp/index.jsp").forward(request, response);

    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long count = ctsprepo.getTotal();
        Long endpage = count/8;
        if(count % 8 !=0){
            endpage ++;
        }
        String indexPage = request.getParameter("index");
        if(indexPage == null){
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        List<ChiTietSp> listctsp = ctsprepo.listpaging(index);
        request.setAttribute("endpage",endpage);
        request.setAttribute("tag",index);
        request.setAttribute("danhsachCTSP",listctsp);
        request.setAttribute("view", "/views/admin/ctsp/ctspShow.jsp");
        request.getRequestDispatcher("/views/admin/ctsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        }else if(uri.contains("update")) {
            this.update(request, response);
        }else {
            response.sendRedirect("/ASS_Java4/ctsp/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        chitietSPViewModel ctspviewmodel = new chitietSPViewModel();
        String id = request.getParameter("id");
        try {
            BeanUtils.populate(ctspviewmodel, request.getParameterMap());
            MauSac ms = msrepo.findById(ctspviewmodel.getMauSacID());
            DongSp dsp = dsprepo.findById(ctspviewmodel.getDspID());
            Nsx nsx = nsxrepo.findById(ctspviewmodel.getNsxID());
            SanPham sp = sprepo.findById(ctspviewmodel.getSanPhamID());

            ChiTietSp ctsp = new ChiTietSp();
            ctsp.setId(id);
            ctsp.setNamBh(ctspviewmodel.getNamBh());
            ctsp.setGiaNhap(ctspviewmodel.getGiaNhap());
            ctsp.setSoLuongTon(ctspviewmodel.getSoLuongTon());
            ctsp.setGiaBan(ctspviewmodel.getGiaBan());
            ctsp.setMoTa(ctspviewmodel.getMoTa());
            ctsp.setDongSp(dsp);
            ctsp.setMauSac(ms);
            ctsp.setSanPham(sp);
            ctsp.setNsx(nsx);
            ctsprepo.update(ctsp);
            response.sendRedirect("/ASS_Java4/ctsp/index");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        chitietSPViewModel ctspviewmodel = new chitietSPViewModel();
        List<Nsx> listnsx = nsxrepo.getAll();
        List<DongSp> listdsp =dsprepo.getAll();
        List<SanPham> listsp = sprepo.getAll();
        List<MauSac> listms =  msrepo.getAll();
        try {
            BeanUtils.populate(ctspviewmodel, request.getParameterMap());

            if(validate.isEmpty(request.getParameter("namBh"))) {
                request.setAttribute("nambh", "Năm bán hàng không được để trống");
            }else if(!validate.checkso(request.getParameter("namBh"))){
                request.setAttribute("namso", "Năm bán hàng phải là số ");
            }
            else if(validate.isEmpty(request.getParameter("giaNhap"))){
                request.setAttribute("gianhap", "Giá nhập không được để trống");
            }else if(!validate.checkso(request.getParameter("giaNhap"))){
                request.setAttribute("ktgianhap", "Giá nhập phải là số dương");
            }else if(validate.isEmpty(request.getParameter("giaBan"))){
                request.setAttribute("giaban", "Giá bán không được để trống");
            }else if(!validate.checkso(request.getParameter("giaBan"))){
                request.setAttribute("gia", "Giá bán phải là số dương");
            }else {

                MauSac ms = msrepo.findById(ctspviewmodel.getMauSacID());
                DongSp dsp = dsprepo.findById(ctspviewmodel.getDspID());
                Nsx nsx = nsxrepo.findById(ctspviewmodel.getNsxID());
                SanPham sp = sprepo.findById(ctspviewmodel.getSanPhamID());

                ChiTietSp ctsp = new ChiTietSp();
                String id = UUID.randomUUID().toString();
                ctsp.setId(id);
                ctsp.setNamBh(ctspviewmodel.getNamBh());
                ctsp.setGiaNhap(ctspviewmodel.getGiaNhap());
                ctsp.setSoLuongTon(ctspviewmodel.getSoLuongTon());
                ctsp.setGiaBan(ctspviewmodel.getGiaBan());
                ctsp.setMoTa(ctspviewmodel.getMoTa());
                ctsp.setDongSp(dsp);
                ctsp.setMauSac(ms);
                ctsp.setSanPham(sp);
                ctsp.setNsx(nsx);
                ctsprepo.create(ctsp);
                response.sendRedirect("/ASS_Java4/ctsp/index");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4/ctsp/create");
        }
        request.setAttribute("ctsp",ctspviewmodel);
        request.setAttribute("listms",listms);
        request.setAttribute("listnsx",listnsx);
        request.setAttribute("listdsp",listdsp);
        request.setAttribute("listsp",listsp);
        request.setAttribute("view", "/views/admin/ctsp/ctspCreate.jsp");
        request.getRequestDispatcher("/views/admin/ctsp/index.jsp").forward(request, response);
    }
}
