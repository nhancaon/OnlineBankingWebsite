
package controller.User;

import DAO.CustomerDAO;
import DAO.PaymentAccountDAO;
import business.Customer;
import business.PaymentAccount;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePassword"})
public class ChangePasswordServlet extends HttpServlet {

      CustomerDAO customerDAO = new CustomerDAO();
    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext servletContext = getServletContext();
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }
        String url = "/changePassword.jsp";
        if (action.equals("changePassword")) {
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("confirmPassword");
   
            
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
