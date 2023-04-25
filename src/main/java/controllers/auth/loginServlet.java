package controllers.auth;

import Utils.MaHoa;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repositories.impl.khachhangRepo;
import repositories.impl.nhanvienRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "login", value = "/login")
public class loginServlet extends HttpServlet {
    khachhangRepo khrepo;
    public loginServlet() {
        khrepo = new khachhangRepo();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String password = request.getParameter("password");
        request.setAttribute("ma",ma);
        request.setAttribute("pass",password);
        String mk = MaHoa.toSHA1(password);
        System.out.println(mk);
        KhachHang kh = this.khrepo.login(ma, mk);
        HttpSession session = request.getSession();
        session.setAttribute("ma", ma);

        if (kh != null) {

            session.setAttribute("kh", kh);
            response.sendRedirect("/ASS_Java4/home/index");

            request.getSession().setAttribute("mess","Login thanh cong");
        } else {
            request.getSession().setAttribute("error", "Nhập đúng Gmail và Password để đăng nhập !");
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        }

    }
}
