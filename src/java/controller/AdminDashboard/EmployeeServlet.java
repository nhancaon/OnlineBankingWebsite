package controller.AdminDashboard;

import business.Employee;
import DAO.EmployeeDAO;
import Exception.HandleException;
import business.Customer;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/employee")
public class EmployeeServlet extends HttpServlet {

    EmployeeDAO employeeDAO = new EmployeeDAO();

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
            case "show-employee" ->
                this.showEmployee(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/employee.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void addEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String fullName = request.getParameter("name");
        String citizenId = request.getParameter("citizenId");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

    }

      protected void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
