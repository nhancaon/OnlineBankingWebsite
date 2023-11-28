package controller.User;

import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import Exception.HandleException;
import business.Customer;
import business.PaymentAccount;
import common.MailSender;
import jakarta.mail.MessagingException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "RecorverPasswordServlet", urlPatterns = {"/recover-password"})
public class RecorverPasswordServlet extends HttpServlet {

    CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/login.jsp";
        String email = request.getParameter("email");

        String to = email;
        String subject = "Welcome to NND Banking";
        String body = "Thank you for using our services. Here is your recover password"
                + "You can use that password to access to our services at NND Banking.\n\n"
                + "If you have any questions about our products or services, please feel free to contact us at any time.\n\n"
                + "Sincerely,\n\n" + "NND Banking";
        try {
            MailSender.sendMail(to, subject, body);
        } catch (MessagingException ex) {
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        servletContext.getRequestDispatcher(url)
                .forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
