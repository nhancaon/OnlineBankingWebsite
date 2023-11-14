package controller.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import business.Customer;
import DAO.CustomerDAO;
import Exception.SignupException;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {

    CustomerDAO customerDAO = new CustomerDAO();

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
            try {
                customerDAO.customerSignup(fullName, email, password, citizenIdentity, phoneNumber, dateOfBirth, address);
                // Successful signup
                request.setAttribute("successMessage", "The account has been created successfully.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } catch (SignupException e) {
                // Handle the exception
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
