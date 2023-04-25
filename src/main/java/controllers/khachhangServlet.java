package controllers;

import Utils.MaHoa;
import Utils.validate;
import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.khachhangRepo;


import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(
        {
                "/kh/index",    // GET
                "/kh/create",   // GET
                "/kh/edit",     // GET
                "/kh/delete",   // GET
                "/kh/store",    // POST
                "/kh/update"
        }
)
public class khachhangServlet extends HttpServlet {
    khachhangRepo khrepo ;
    public khachhangServlet(){
        khrepo = new khachhangRepo();
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
        }
        else if (uri.contains("edit")) {
            this.edit(request, response);
        }
        else if (uri.contains("delete")) {
            this.delete(request, response);
        }else {
            this.index(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idkh = request.getParameter("id");
        try {
            KhachHang entity = this.khrepo.findById(idkh);
            this.khrepo.delete(entity);
            response.sendRedirect("/ASS_Java4/kh/index");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro delete");
            this.index(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idsp = request.getParameter("id");
        try {
            KhachHang kh = khrepo.findById(idsp);
            request.setAttribute("kh",kh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("view", "/views/admin/khachhang/khachhangEdit.jsp");
        request.getRequestDispatcher("/views/admin/khachhang/index.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/views/admin/khachhang/khachhangCreate.jsp");
        request.getRequestDispatcher("/views/admin/khachhang/index.jsp").forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<KhachHang> list = khrepo.getAll();
        request.setAttribute("danhsachKH",list);
        request.setAttribute("view", "/views/admin/khachhang/khachhangShow.jsp");
        request.getRequestDispatcher("/views/admin/khachhang/index.jsp").forward(request, response);
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
            response.sendRedirect("/ASS_Java4/kh/index");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        String idstr = request.getParameter("id");
        String matkhau = request.getParameter("matKhau");
        String mk = MaHoa.toSHA1(matkhau);
        KhachHang kh = new KhachHang();
        try {
            BeanUtils.populate(kh, request.getParameterMap());
            kh.setId(idstr);
            kh.setMatKhau(mk);
            khrepo.update(kh);
            response.sendRedirect("/ASS_Java4/kh/index");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        KhachHang kh = new KhachHang();

        try {
            BeanUtils.populate(kh,
                    request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (validate.isEmpty(kh.getMa())) {
            request.setAttribute("ma", "Mã không được để trống");
        } else if (validate.isEmpty(kh.getTen())) {
            request.setAttribute("ten", "Tên không được để trống");
        }
            else if(validate.isEmpty(String.valueOf(kh.getNgaySinh()))) {
                request.setAttribute("ngaysinh", "Ngày sinh không được để trống");
            }
        else if (validate.isEmpty(kh.getDiaChi())) {
            request.setAttribute("dc", "Địa chỉ không được để trống");
        } else if (validate.isEmpty(kh.getSdt())) {
            request.setAttribute("dt", "Điện thoại không được để trống");
        } else if (!kh.getSdt().matches("0\\d{9}")) {

            request.setAttribute("sdt", "Số điện thoại không hợp lệ");
        } else {
            try {
                String id = UUID.randomUUID().toString();
                String mk = MaHoa.toSHA1(kh.getMatKhau());
                kh.setId(id);
                kh.setMatKhau(mk);
                khrepo.create(kh);
                response.sendRedirect("/ASS_Java4/kh/index");
                return;

        } catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("/ASS_Java4/kh/create");
        }
    }
        request.setAttribute("kh",kh);
        request.setAttribute("view", "/views/admin/khachhang/khachhangCreate.jsp");
        request.getRequestDispatcher("/views/admin/khachhang/index.jsp").forward(request,response);
    }
}
