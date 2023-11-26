package controller.AdminDashboard;
import business.Customer;
import DAO.CustomerDAO;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/customer")
public class CustomerServlet extends HttpServlet {

    CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "show-customer" -> this.showCustomer(request, response);
            default -> {}
        }
        url = "/admin-dashboard/customer.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> customers = customerDAO.findAllCustomer();
        
        request.setAttribute("customers", customers);
        
    }

}
