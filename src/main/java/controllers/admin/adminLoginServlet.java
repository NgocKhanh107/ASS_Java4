package controllers.admin;

import Utils.MaHoa;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.impl.nhanvienRepo;

import java.io.IOException;

@WebServlet(name = "loginAdmin", value = "/AdminSigninServlet")
public class adminLoginServlet extends HttpServlet {
    nhanvienRepo nvrepo;
    public adminLoginServlet(){
        nvrepo = new nhanvienRepo();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/views/admin/loginAdmin/loginAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String ma = request.getParameter("ma");
        String password = request.getParameter("password");
        String mk = MaHoa.toSHA1(password);
        NhanVien nv = this.nvrepo.login(ma, mk);

        if (nv != null) {
            HttpSession session = request.getSession();
            session.setAttribute("nv", nv);
            response.sendRedirect("/ASS_Java4/ch/index");
            System.out.println("dang nhap thanh cong");
            request.getSession().setAttribute("mess","Login thanh cong");
        } else {
            request.getSession().setAttribute("error", "Nhập đúng Gmail và Password để đăng nhập !");
            request.getRequestDispatcher("/views/admin/loginAdmin/loginAdmin.jsp").forward(request, response);
        }


    }
}
