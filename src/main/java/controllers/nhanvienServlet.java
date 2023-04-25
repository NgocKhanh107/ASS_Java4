package controllers;

import Utils.MaHoa;
import Utils.validate;
import entities.ChucVu;
import entities.CuaHang;
import entities.NhanVien;
import entities.SanPham;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.NhanvienModel;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.chucvuRepo;
import repositories.impl.cuahangRepo;
import repositories.impl.nhanvienRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(
        { 	    "/nv/index",
                "/nv/show",
                "/nv/create",
                "/nv/edit",
                "/nv/update",
                "/nv/delete",
                "/nv/store" }
)
public class nhanvienServlet extends HttpServlet {
    chucvuRepo cvrepo ;
    cuahangRepo chrepo ;

    nhanvienRepo nvrepo;
    public nhanvienServlet(){
        nvrepo = new nhanvienRepo();
        cvrepo = new chucvuRepo();
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
        String idnv = request.getParameter("id");
        try {
            NhanVien entity = this.nvrepo.findById(idnv);
            this.nvrepo.delete(entity);
            response.sendRedirect("/ASS_Java4/nv/index");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro delete");
            this.index(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idsp = request.getParameter("id");
        List<ChucVu> listCV = cvrepo.getAll();
        List<CuaHang> listCH = chrepo.getAll();
        try {
            NhanVien nv = nvrepo.findById(idsp);
            request.setAttribute("nv",nv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("listcv",listCV);
        request.setAttribute("listch",listCH);
        request.setAttribute("view", "/views/admin/nhanvien/nhanvienEdit.jsp");
        request.getRequestDispatcher("/views/admin/nhanvien/index.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ChucVu> listCV = cvrepo.getAll();
        List<CuaHang> listCH = chrepo.getAll();
        request.setAttribute("listcv",listCV);
        request.setAttribute("listch",listCH);
        request.setAttribute("view", "/views/admin/nhanvien/nhanvienCreate.jsp");
        request.getRequestDispatcher("/views/admin/nhanvien/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NhanVien> list = nvrepo.getAll();
        request.setAttribute("danhsachNV",list);
        request.setAttribute("view", "/views/admin/nhanvien/nhanvienShow.jsp");
        request.getRequestDispatcher("/views/admin/nhanvien/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        }else if (uri.contains("update")){
            this.update(request, response);
        }else{
            response.sendRedirect("/ASS_Java4/nv/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NhanvienModel nv = new NhanvienModel();
        String idnv = request.getParameter("id");
        try {
            BeanUtils.populate(nv,request.getParameterMap());
            String mk = MaHoa.toSHA1(nv.getMatkhau());
            NhanVien nhanvien = new NhanVien();
            nhanvien.setId(idnv);
            nhanvien.setMa(nv.getMa());
            nhanvien.setTen(nv.getTen());
            nhanvien.setTendem(nv.getTendem());
            nhanvien.setHo(nv.getHo());

            nhanvien.setGioitinh(nv.getGioitinh());
            nhanvien.setNgaysinh(Date.valueOf(nv.getNgaysinh()));
            nhanvien.setDiachi(nv.getDiachi());
            nhanvien.setSdt(nv.getSdt());
            nhanvien.setMatkhau(mk);
            CuaHang ch = chrepo.findById(nv.getCuaHangID());
            nhanvien.setCuaHang(ch);
            ChucVu cv = cvrepo.findById(nv.getChucVuID());
            nhanvien.setChucVu(cv);
            nvrepo.update(nhanvien);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/ASS_Java4/nv/index");
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        NhanvienModel nv = new NhanvienModel();
        List<ChucVu> listCV = cvrepo.getAll();
        List<CuaHang> listCH = chrepo.getAll();
        try {
            BeanUtils.populate(nv,request.getParameterMap());
            if (validate.isEmpty(nv.getMa())) {
                request.setAttribute("ma", "Mã không được để trống");

            }
            else if(validate.isEmpty(nv.getTen())) {
                request.setAttribute("ten", "Tên không được để trống");
            }
            else if(validate.isEmpty(nv.getHo())) {
                request.setAttribute("ho", "Họ không được để trống");
            }
            else if(validate.isEmpty(nv.getGioitinh())) {
                request.setAttribute("gioitinh", "Giới tính chưa được chọn");
            }
            else if(validate.isEmpty(nv.getNgaysinh())) {
                request.setAttribute("ngaysinh", "Ngày sinh không được để trống");
            }
            else if(validate.isEmpty(nv.getDiachi())) {
                request.setAttribute("dc", "Địa chỉ không được để trống");
            }
            else if(validate.isEmpty(nv.getSdt())) {
                request.setAttribute("dt", "Điện thoại không được để trống");
            }
            else if (!nv.getSdt().matches("0\\d{9}")) {

                request.setAttribute("sdt", "Số điện thoại không hợp lệ");
            }else {
                String id = UUID.randomUUID().toString();
                String mk = MaHoa.toSHA1(nv.getMatkhau());
                NhanVien nhanvien = new NhanVien();
                nhanvien.setId(id);
                nhanvien.setMa(nv.getMa());
                nhanvien.setTen(nv.getTen());
                nhanvien.setTendem(nv.getTendem());
                nhanvien.setHo(nv.getHo());
                nhanvien.setGioitinh(nv.getGioitinh());
                nhanvien.setNgaysinh(Date.valueOf(nv.getNgaysinh()));
                nhanvien.setDiachi(nv.getDiachi());
                nhanvien.setSdt(nv.getSdt());
                nhanvien.setMatkhau(mk);
                CuaHang ch = chrepo.findById(nv.getCuaHangID());
                nhanvien.setCuaHang(ch);
                ChucVu cv = cvrepo.findById(nv.getChucVuID());
                nhanvien.setChucVu(cv);
                nvrepo.create(nhanvien);
                response.sendRedirect("/ASS_Java4/nv/index");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4/nv/create");
        }
        request.setAttribute("nv",nv);
        request.setAttribute("listcv",listCV);
        request.setAttribute("listch",listCH);
        request.setAttribute("view", "/views/admin/nhanvien/nhanvienCreate.jsp");
        request.getRequestDispatcher("/views/admin/nhanvien/index.jsp").forward(request,response);
    }
}
