package controller.User;

import business.Customer;
import DAO.CustomerDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    CustomerDAO customerDAO = new CustomerDAO();

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
            Customer customer = customerDAO.checkLogin(email, password);
            if (customer == null) {
                response.sendRedirect("login.jsp");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("customer", customer);
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
