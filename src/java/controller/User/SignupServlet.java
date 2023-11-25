package controller.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import business.Customer;
import DAO.CustomerDAO;
import Exception.HandleException;

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
        }
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
