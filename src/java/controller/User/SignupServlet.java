package controller.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import DAO.CustomerDAO;
import common.MailSender;
import Exception.HandleException;
import jakarta.mail.MessagingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {

    CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        String url = "/signup.jsp";
        if (action.equals("signup")) {

            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String citizenIdentity = request.getParameter("citizenIdentity");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String address = request.getParameter("address");
            int pinNumber = Integer.parseInt(request.getParameter("pinNumber"));
            try {
                customerDAO.customerSignup(fullName, email, password, citizenIdentity, phoneNumber, dateOfBirth, address, pinNumber);
                request.setAttribute("successMessage", "The account has been created successfully.");

            } catch (HandleException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }

            String to = email;
            String subject = "Welcome to NND Banking";
            String body = "Dear " + fullName + ",\n\n"
                    + "Thank you for creating an account with us. Your account is ready for use. "
                    + "You can now start use our services at NND Banking.\n\n"
                    + "If you have any questions about our products or services, please feel free to contact us at any time.\n\n"
                    + "Sincerely,\n\n" + "NND Banking";
            try {
                MailSender.sendMail(to, subject, body); 
            } catch (MessagingException ex) {
                Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
