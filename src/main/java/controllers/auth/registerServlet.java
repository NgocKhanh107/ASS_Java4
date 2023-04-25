package controllers.auth;

import Utils.MaHoa;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.khachhangRepo;
import repositories.impl.nhanvienRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "register", value = "/register")
public class registerServlet extends HttpServlet {
    khachhangRepo khrepo;
    public registerServlet(){
        khrepo = new khachhangRepo();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/auth/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String mk = request.getParameter("matKhau");
        String ma = request.getParameter("ma");
        String matkhau = MaHoa.toSHA1(mk);
        KhachHang kh = new KhachHang();
        String id = UUID.randomUUID().toString();
        kh.setId(id);
        kh.setMa(ma);
        kh.setMatKhau(matkhau);
        khrepo.create(kh);
        response.sendRedirect("/ASS_Java4/login");
//        try {
//            BeanUtils.populate(kh,request.getParameterMap());
//            String id = UUID.randomUUID().toString();
//            kh.setId(id);
//            System.out.println("mk"+kh.getMatKhau());
//            String mk = MaHoa.toSHA1(kh.getMatKhau());
//            kh.setMatKhau(mk);
//            khrepo.create(kh);
//            response.sendRedirect("/ASS_Java4/login");
//            System.out.println("dang ký thanh cong");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
