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
            case "delete" -> {
                this.deleteEmployee(request, response);
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
        String employeeId = request.getParameter("employeeIdUpdate");
        Employee employee = employeeDAO.findByEmployeeId(employeeId);
        System.out.println("roleUpdate " + request.getParameter("roleUpdate"));

        String email, fullName, citizenId, phoneNumber, address, dateOfBirth, password, role;

        if(!request.getParameter("emailUpdate").isEmpty()){
            email = request.getParameter("emailUpdate");
        }else{
            email = employee.getEmail();
        }
        if(!request.getParameter("nameUpdate").isEmpty()){
            fullName = request.getParameter("nameUpdate");
        }else{
            fullName = employee.getName();
        }
        if(!request.getParameter("citizenIdUpdate").isEmpty()){
            citizenId = request.getParameter("citizenIdUpdate");
        }else{
            citizenId = employee.getCitizenId();
        }
        if(!request.getParameter("phoneNumberUpdate").isEmpty()){
            phoneNumber = request.getParameter("phoneNumberUpdate");
        }else{
            phoneNumber = employee.getPhoneNumber();
        }
        if(!request.getParameter("addressUpdate").isEmpty()){
            address = request.getParameter("addressUpdate");
        }else{
            address = employee.getAddress();
        }
        if(!request.getParameter("dateOfBirthUpdate").isEmpty()){
            dateOfBirth = request.getParameter("dateOfBirthUpdate");
        }else{
            dateOfBirth = String.valueOf(employee.getDateofBirth());
        }
        if(!request.getParameter("passwordUpdate").isEmpty()){
            password = request.getParameter("passwordUpdate");
        }else{
            password = employee.getPassword();
        }
        if(request.getParameter("roleUpdate") != null){
            role = request.getParameter("roleUpdate");
        }else{
            role = employee.getRoles();
        }

        try {
            employeeDAO.checkUpdateEmployee(employeeId, fullName, email, password, citizenId, phoneNumber, dateOfBirth, address, role);
            request.setAttribute("successMessage", "The employee has been updated successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

    protected void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("employeeId");
        employeeDAO.delete(employeeId);
    }
}
