package controller.AdminDashboard;
import business.Employee;
import DAO.EmployeeDAO;

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
            case "show-employee" -> this.showInterestRate(request, response);
            default -> {}
        }
        url = "/admin-dashboard/employee.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showInterestRate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // List<Employee> employees = EmployeeDAO.findAllInterestRate();

        // request.setAttribute("employees", employees);
    }

}
