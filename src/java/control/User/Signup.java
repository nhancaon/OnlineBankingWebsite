package control.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import business.Customer;
import data.CustomerDB;

@WebServlet("/Signup")
public class Signup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("signup")) {
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String citizenIdentity = request.getParameter("citizenIdentity");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String address = request.getParameter("address");
            
            CustomerDB.customerSignup(fullName, email, password, citizenIdentity, phoneNumber, dateOfBirth, address);
            
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
