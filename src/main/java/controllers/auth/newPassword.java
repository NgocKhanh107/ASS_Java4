package controllers.auth;

import Utils.MaHoa;
import entities.KhachHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import repositories.impl.khachhangRepo;

import java.io.IOException;

@WebServlet(name = "newPass", value = "/newPass")

public class newPassword extends HttpServlet {
    private khachhangRepo khrepo ;
    public newPassword(){
        this.khrepo = new khachhangRepo();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ma = (String) session.getAttribute("maNew");
        System.out.println("ma new: "+ma);
        KhachHang khachHang = khrepo.findIDByMa(ma);
        System.out.println(khachHang.getId());
        String newPass = request.getParameter("newPass");
        String confPassword = request.getParameter("confPassword");
        String mk = MaHoa.toSHA1(confPassword);
        if (newPass != null && confPassword != null && newPass.equals(confPassword)) {
            KhachHang entity = new KhachHang();
//            String ma = (String) session.getAttribute("ma");
//            System.out.println(ma);
            entity.setId(khachHang.getId());
            entity.setMatKhau(mk);
            entity.setMa(ma);
            khrepo.updatePass(khachHang.getId(),mk);
            request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
        }
    }
}
