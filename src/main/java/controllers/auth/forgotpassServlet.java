package controllers.auth;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet(name = "fogotpassword", value = "/fogotpassword")
public class forgotpassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String ma = request.getParameter("ma");
        request.setAttribute("ma",ma);
        request.getRequestDispatcher("/views/auth/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        final String username = "ngockhanhdn107@gmail.com";
        final String password = "uqgeraducfsrbxvz";
        RequestDispatcher dispatcher = null;
        int otpvalue = 0;
        HttpSession mySession = request.getSession();
        Random rand = new Random();
        otpvalue = rand.nextInt(1255650);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("ngockhanhdn107@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ngockhanhdn107@gmail.com"));
            message.setSubject("Đổi mật khẩu","UTF-8");

            message.setText("Dear Người Nhận,"
                    + "\n\n Đây là mã OTP: "+otpvalue, "UTF-8", "HTML");

            Transport.send(message);

            System.out.println("Gửi email thành công!");

        } catch (MessagingException  e) {
            throw new RuntimeException(e);
        }
        dispatcher = request.getRequestDispatcher("/views/auth/EnterOtp.jsp");
        request.setAttribute("message","OTP đã được gửi tới email ");
        String ma = request.getParameter("ma");
        mySession.setAttribute("otp",otpvalue);
        HttpSession sessionPass = request.getSession();
        sessionPass.setAttribute("maNew",ma);
//        mySession.setAttribute("ma",ma);
        dispatcher.forward(request, response);
    }
}
