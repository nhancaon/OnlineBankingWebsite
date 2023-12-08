package controller.AdminDashboard;

import business.Beneficiary;
import business.Customer;
import DAO.BeneficiaryDAO;
import DAO.BeneficiaryDAO;
import Exception.HandleException;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/beneficiary")
public class BeneficiaryServlet extends HttpServlet {

    BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

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
            case "update-beneficiary" -> {
                this.updateBeneficiary(request, response);
                this.showBeneficiary(request, response);
            }
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

    }

    protected void updateBeneficiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumberUpdate");
        String beneficiaryId = request.getParameter("beneficiaryIdUpdate");
        Beneficiary beneficiary = beneficiaryDAO.findExistingBeneficiary(accountNumber);

        String name;

        if (!request.getParameter("nameUpdate").isEmpty()) {
            name = request.getParameter("nameUpdate");
        } else {
            name = beneficiary.getName();
        }

        try {
            beneficiaryDAO.checkUpdateBeneficiary(beneficiaryId, name, accountNumber);
            request.setAttribute("successMessage", "The beneficiary has been updated successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

    protected void deleteBeneficiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beneficiaryId = request.getParameter("beneficiaryId");
        beneficiaryDAO.delete(beneficiaryId);
    }
}
