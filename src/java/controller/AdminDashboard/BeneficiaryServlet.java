package controller.AdminDashboard;

import business.Beneficiary;
import DAO.BeneficiaryDAO;
import DAO.BeneficiaryDAO;
import Exception.HandleException;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/beneficiary")
public class BeneficiaryServlet extends HttpServlet {

    BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "delete" -> {
                this.deleteBeneficiary(request, response);
                this.showBeneficiary(request, response);
            }

            default -> {
            }
        }
        url = "/admin-dashboard/beneficiary.jsp";
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
            case "show-beneficiary" ->
                this.showBeneficiary(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/beneficiary.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showBeneficiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Beneficiary> beneficiaries = beneficiaryDAO.findAllBeneficiary();
        request.setAttribute("beneficiaries", beneficiaries);
    }

    protected void addBeneficiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String email = request.getParameter("email");
        // String fullName = request.getParameter("name");
        // String citizenId = request.getParameter("citizenId");
        // String phoneNumber = request.getParameter("phoneNumber");
        // int pinNumber = Integer.parseInt(request.getParameter("pinNumber"));
        // try {
        //     customerDAO.customerSignup(fullName, email, password, citizenId, phoneNumber, dateOfBirth, address,
        //             pinNumber);
        //     request.setAttribute("successMessage", "The customer has been added successfully.");

        // } catch (HandleException e) {
        //     request.setAttribute("errorMessage", e.getMessage());
        // }
    }

    protected void updateBeneficiary(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void deleteBeneficiary(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int beneficiaryId = Integer.parseInt(request.getParameter("beneficiaryId"));

        beneficiaryDAO.delete(beneficiaryId);
    }

}
