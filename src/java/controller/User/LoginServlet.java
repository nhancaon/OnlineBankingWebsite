package controller.User;

import business.Customer;
import DAO.CustomerDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Customer customer = CustomerDAO.customerLogin(email, password);
            if (customer == null) {
                response.sendRedirect("login.jsp");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                String customerName = customer.getName();
                String citizenId = customer.getCitizenId();

                // Store the details in the session
                session.setAttribute("customerName", customerName);
                session.setAttribute("citizenId", citizenId);
                response.sendRedirect("profile.jsp");

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
