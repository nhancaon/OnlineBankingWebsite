package controller.AdminDashboard;

import business.Customer;
import DAO.CustomerDAO;
import Exception.HandleException;

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
        ServletContext servletContext = getServletContext();
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "add-customer" -> {
                this.addCustomer(request, response);
                this.showCustomer(request, response);
            }
            case "update-customer" -> {
                this.updateCustomer(request, response);
                this.showCustomer(request, response);
            }
            default -> {
            }
        }
        url = "/admin-dashboard/customer.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "show-customer" ->
                this.showCustomer(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/customer.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerDAO.findAllCustomer();
        request.setAttribute("customers", customers);
    }

    protected void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("name");
        String citizenId = request.getParameter("citizenId");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String password = request.getParameter("password");
        int pinNumber = Integer.parseInt(request.getParameter("pinNumber"));

        try {
            customerDAO.customerSignup(fullName, email, password, citizenId, phoneNumber, dateOfBirth, address, pinNumber);
            request.setAttribute("successMessage", "The customer has been added successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

    protected void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerIdUpdate");
        Customer customer = customerDAO.findByCustomerId(customerId);

        String email;
        String fullName;
        String citizenId;
        String phoneNumber;
        String address;
        String dateOfBirth;
        String password;
        int pinNumber;

        if(!request.getParameter("emailUpdate").isEmpty()){
            email = request.getParameter("emailUpdate");
        }else{
            email = customer.getEmail();
        }
        
        if(!request.getParameter("nameUpdate").isEmpty()){
            fullName = request.getParameter("nameUpdate");
        }else{
            fullName = customer.getName();
        }

        if(!request.getParameter("citizenIdUpdate").isEmpty()){
            citizenId = request.getParameter("citizenIdUpdate");
        }else{
            citizenId = customer.getCitizenId();
        }

        if(!request.getParameter("phoneNumberUpdate").isEmpty()){
            phoneNumber = request.getParameter("phoneNumberUpdate");
        }else{
            phoneNumber = customer.getPhoneNumber();
        }

        if(!request.getParameter("addressUpdate").isEmpty()){
            address = request.getParameter("addressUpdate");
        }else{
            address = customer.getAddress();
        }

        if(!request.getParameter("dateOfBirthUpdate").isEmpty()){
            dateOfBirth = request.getParameter("dateOfBirthUpdate");
        }else{
            dateOfBirth = String.valueOf(customer.getDateofBirth());
        }

        if(!request.getParameter("passwordUpdate").isEmpty()){
            password = request.getParameter("passwordUpdate");
        }else{
            password = customer.getPassword();
        }

        if(!request.getParameter("pinNumberUpdate").isEmpty()){
            pinNumber = Integer.parseInt(request.getParameter("pinNumberUpdate"));
        }else{
            pinNumber = customer.getPinNumber();
        }

        System.out.println("customerId "+customerId);
        System.out.println("email "+ email);
        System.out.println("fullName "+fullName);
        System.out.println("citizenId "+citizenId);
        System.out.println("phoneNumber "+phoneNumber);
        System.out.println("address "+address);       
        System.out.println("dateOfBirth" + dateOfBirth);
        System.out.println("password "+password);
        System.out.println("pinNumber "+pinNumber);

        try {
            customerDAO.checkUpdateCustomer(customerId, citizenId, email, fullName, password, phoneNumber, dateOfBirth, address, pinNumber);
            request.setAttribute("successMessage", "The customer has been updated successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }
  
}
