package controllers.admin;

import Utils.validate;
import entities.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.impl.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/home/index",
                "/home/detailSP",
                "/home/add",
                "/home/dsGioHang",
                "/home/store",
                "/home/hoadon",
                "/home/dshoadon",
                "/home/themxoa",
                "/home/loi",
                "/home/delete"
        }
)
public class homeServlet extends HttpServlet {
    hoadonRepo hdrepo;
    giohangRepo giohangrepo;
    chitietSpRepo ctsprepo ;
    hdctRepo hdctrepo;
    khachhangRepo khrepo;
    ghctRepo ghctrepo;
    public homeServlet(){
        ctsprepo = new chitietSpRepo();
        hdrepo = new hoadonRepo();
        giohangrepo = new giohangRepo();
        hdctrepo = new hdctRepo();
        khrepo = new khachhangRepo();
        ghctrepo = new ghctRepo();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
            this.index(request, response);
        }else if (uri.contains("detailSP")) {
            this.detailSP(request, response);
        }else if (uri.contains("dsGioHang")) {
            this.dsGiohang(request, response);
        }else if (uri.contains("dshoadon")) {
            this.dshoadon(request, response);
        }else if (uri.contains("loi")) {
            this.loi(request, response);
        }else if (uri.contains("themxoa")) {
            this.themxoa(request, response);
        }else if (uri.contains("delete")) {
            this.delete(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        String idgh = request.getParameter("idgh");
        String idctsp = request.getParameter("idctsp");
        try {

            this.ghctrepo.remove(idgh,idctsp);
            response.sendRedirect("/ASS_Java4/home/dsGioHang");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro delete");

        }
    }

    private void themxoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        String idctsp = request.getParameter("idctsp");
        String idgh = request.getParameter("idgh");
        List<GioHangChiTiet> listghct = ghctrepo.getAll();
        if (action != null && idctsp != null && idgh != null) {
            if (action.equals("inc")) {
              for(GioHangChiTiet ghct : listghct){
                  if (ghct.getIdChiTietSp().getId().equals(idctsp) && ghct.getIdGioHang().getId().equals(idgh)) {
                      int quantity = ghct.getSoLuong();
                      quantity++;
                      ghct.setSoLuong(quantity);
                      break;

                  }
              }
                response.sendRedirect("/ASS_Java4/home/dsGioHang");
            }

            if (action.equals("dec")) {
                for(GioHangChiTiet ghct : listghct){
                    if (ghct.getIdChiTietSp().getId().equals(idctsp) && ghct.getIdGioHang().getId().equals(idgh)) {
                        int quantity = ghct.getSoLuong();
                        quantity--;
                        ghct.setSoLuong(quantity);
                        break;

                    }
                }
                response.sendRedirect("/ASS_Java4/home/dsGioHang");
            }
        }
    }

    private void loi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/banhang/loi.jsp");
        request.getRequestDispatcher("/views/banhang/index.jsp").forward(request, response);
    }

    private void dshoadon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listdshd",hdctrepo.getAll());
        request.setAttribute("view", "/views/banhang/hoadon.jsp");
        request.getRequestDispatcher("/views/banhang/index.jsp").forward(request, response);
    }

    private void dsGiohang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listdsgiohang",ghctrepo.getAll());
        request.setAttribute("view", "/views/banhang/dsGioHang.jsp");
        request.getRequestDispatcher("/views/banhang/index.jsp").forward(request, response);
    }

    private void detailSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<GioHang> list = giohangrepo.getAll();

        HttpSession session = request.getSession();
        GioHang gh= (GioHang) session.getAttribute("giohang");
        KhachHang kh = (KhachHang) session.getAttribute("kh");
        int so = list.size()+1;
        String maGH = "GH".concat(String.valueOf(so));
        if(kh!=null) {
            if (gh == null) {
                gh = new GioHang();
                gh.setId(UUID.randomUUID().toString());
                gh.setMa(maGH);
                gh.setNgayTao(new Date());
                gh.setKhachHang(kh);
                giohangrepo.create(gh);
                session.setAttribute("giohang", gh);
            }

            try {
                ChiTietSp ctsp = ctsprepo.findById(id);
                request.setAttribute("ctsp", ctsp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("view", "/views/banhang/detailSP.jsp");
            request.getRequestDispatcher("/views/banhang/index.jsp").forward(request, response);
        }else {
            response.sendRedirect("/ASS_Java4/login");
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<GioHangChiTiet> listghct = ghctrepo.getAll();
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
        session.setAttribute("total",listghct.size());
        request.setAttribute("tag",index);
        request.setAttribute("danhsachSP",ctsprepo.getAll());
        request.setAttribute("danhsachSP",listctsp);
        request.setAttribute("view", "/views/banhang/sanpham.jsp");
        request.getRequestDispatcher("/views/banhang/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            try {
                this.store(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (uri.contains("hoadon")) {
            this.hoadon(request, response);
        }
    }

    private void hoadon(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String[] selectedProducts = request.getParameterValues("sanpham");
        List<GioHangChiTiet> listghct = ghctrepo.getAll();
        List<HoaDon> listhd = hdrepo.getAll();
        List<HoaDonChiTiet> listhdct = hdctrepo.getAll();

        int so = listhd.size()+1;
        String maHD = "GH".concat(String.valueOf(so));
        HttpSession session = request.getSession();
        KhachHang kh = (KhachHang) session.getAttribute("kh");
        HoaDon hd = (HoaDon) session.getAttribute("hoadon");
        if (hd == null) {
            hd = new HoaDon();
            hd.setId( UUID.randomUUID().toString());
            hd.setMa(maHD);
            hd.setNgayTao(new Date());
            hd.setKhachHang(kh);
            hdrepo.create(hd);
            session.setAttribute("hoadon", hd);
        }


        if(selectedProducts != null){
            if(listhdct.size() == 0) {
                for (String selectedProduct : selectedProducts) {
                    String[] parts = selectedProduct.split(",");
                    String ctspId = parts[0];
                    String giohangId = parts[1];

                    GioHangChiTiet ghct = ghctrepo.findByIDGHCT(giohangId, ctspId);
                    ChiTietSp ctsp = ctsprepo.findById(ctspId);
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setIdChiTietSp(ctsp);
                    hdct.setIdHoaDon(hd);
                    hdct.setSoLuong(ghct.getSoLuong());
                    hdct.setDonGia(ghct.getDonGia());
                    hdctrepo.create(hdct);

                }
            }
            else{

                for(String selectedProduct : selectedProducts){
//                    for(HoaDonChiTiet hdct : listhdct){
                        String[] parts = selectedProduct.split(",");
                        String ctspId = parts[0];
                        String giohangId = parts[1];

                            HoaDonChiTiet hdct = hdctrepo.findByID(ctspId,hd.getId());
                            if(hdct == null){
                                GioHangChiTiet ghct = ghctrepo.findByIDGHCT(giohangId, ctspId);
                                ChiTietSp ctsp = ctsprepo.findById(ctspId);
                                HoaDonChiTiet hdCT = new HoaDonChiTiet();
                                hdCT.setIdChiTietSp(ctsp);
                                hdCT.setIdHoaDon(hd);
                                hdCT.setSoLuong(ghct.getSoLuong());
                                hdCT.setDonGia(ghct.getDonGia());
                                hdctrepo.create(hdCT);
                            }else{

                                    try{
                                        String sl = request.getParameter("sl");
                                        String idhd = hdct.getIdHoaDon().getId();
                                    if(!validate.checkso(sl)){
                                        request.setAttribute("soluong", "Số lượng không được nhỏ hơn 0> Vui lòng nhập lại");
                                    }
                                    else {

                                        String idctsp = hdct.getIdChiTietSp().getId();
                                        Integer soLuong = hdct.getSoLuong() + Integer.parseInt(sl);
                                        hdctrepo.updateSL(idhd, idctsp, soLuong);
                                        response.sendRedirect("/ASS_Java4/home/dshoadon");
                                        return;
                                    }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            response.sendRedirect("/ASS_Java4/home/dsGioHang");
                                        }

                                request.setAttribute("listdsgiohang",ghctrepo.getAll());
                                request.setAttribute("view", "/views/banhang/dsGioHang.jsp");
                                request.getRequestDispatcher("/views/banhang/index.jsp").forward(request, response);
                            }
                    }
            }
        }
        response.sendRedirect("/ASS_Java4/home/dshoadon");

    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        ChiTietSp ctsp = ctsprepo.findById(id);
        HttpSession session = request.getSession();
        KhachHang kh = (KhachHang) session.getAttribute("kh");
        GioHang gh = (GioHang) session.getAttribute("giohang");

        String slNhap = request.getParameter("sl");
        int sl = Integer.parseInt(slNhap);
        GioHangChiTiet ghct = new GioHangChiTiet();
        ghct.setSoLuong(sl);
        ghct.setIdChiTietSp(ctsp);
        ghct.setIdGioHang(gh);
        ghct.setDonGia(ctsp.getGiaBan());


        List<GioHang> listgh = giohangrepo.findByIdKH(kh.getId());
        List<GioHangChiTiet> listghct = ghctrepo.getAll();

        if(listgh == null){
            ghctrepo.create(ghct);
            response.sendRedirect("/ASS_Java4/home/dsGioHang");
        }

        else {
            boolean kt = false;
            for(GioHangChiTiet giohangCT : listghct){
                if(giohangCT.getIdChiTietSp().getId().equals(id)){
                    kt = true;
                    response.sendRedirect("/ASS_Java4/home/loi");
                }
            }
            if (!kt) {
                ghctrepo.create(ghct);
                response.sendRedirect("/ASS_Java4/home/dsGioHang");
            }
        }
        List<GioHangChiTiet> listmoi = ghctrepo.getAll();
        session.setAttribute("total",listmoi.size());
    }
}
