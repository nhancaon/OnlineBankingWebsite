package control.User;

import business.Customer;
import data.CustomerDB;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Login")
public class Login extends HttpServlet {

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
            Customer customerLogin = CustomerDB.customerLogin(email, password);
            if (customerLogin == null) {
                response.sendRedirect("login.jsp");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
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
