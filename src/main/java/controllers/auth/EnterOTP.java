package controllers.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "enterOTP", value = "/enterOTP")
public class EnterOTP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            int value = Integer.parseInt(request.getParameter("otp"));
            HttpSession session = request.getSession();
            int otp = (int) session.getAttribute("otp");
            RequestDispatcher dispatcher = null;
            if (value == otp) {
                request.setAttribute("status", "Thành công");
                dispatcher = request.getRequestDispatcher("/views/auth/newPassword.jsp");
                dispatcher.forward(request, response);

            } else {
                request.setAttribute("message", "Thất bại");
                dispatcher = request.getRequestDispatcher("/views/auth/EnterOtp.jsp");
                dispatcher.forward(request, response);
            }

        }
    }
}
