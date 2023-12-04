package controller.AdminDashboard;

import business.Employee;
import DAO.EmployeeDAO;
import Exception.HandleException;

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
        ServletContext servletContext = getServletContext();
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "add-employee" -> {
                this.addEmployee(request, response);
                this.showEmployee(request, response);
            }
            case "update-employee" -> {
                this.updateEmployee(request, response);
                this.showEmployee(request, response);
            }
            default -> {
            }
        }
        url = "/admin-dashboard/employee.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    protected void showEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeDAO.findAllEmployee();
        request.setAttribute("employees", employees);
    }

    protected void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("name");
        String citizenId = request.getParameter("citizenId");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            employeeDAO.createEmployee(fullName, email, password, citizenId, phoneNumber, dateOfBirth, address, role);
            request.setAttribute("successMessage", "The employee has been added successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }

    }

    protected void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
