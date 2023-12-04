package controller.AdminDashboard;

import business.InterestRate;
import business.PaymentAccount;
import DAO.PaymentAccountDAO;
import Exception.HandleException;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/paymentAccount")
public class PaymentAccountServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

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
            case "add-paymentAccount" -> {
                this.addPaymentAccount(request, response);
                this.showPaymentAccount(request, response);
            }
            case "update-paymentAccount" -> {
                this.updatePaymentAccount(request, response);
                this.showPaymentAccount(request, response);
            }
            default -> {
            }
        }
        url = "/admin-dashboard/paymentAccount.jsp";
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
            case "show-paymentAccount" ->
                this.showPaymentAccount(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/paymentAccount.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showPaymentAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PaymentAccount> paymentAccounts = paymentAccountDAO.findAllPaymentAccount();
        request.setAttribute("paymentAccounts", paymentAccounts);
    }

    protected void addPaymentAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String accountNumber = request.getParameter("accountNumber");
        String accountStatus = request.getParameter("accountStatus");
        String accountType = request.getParameter("accountType");
        String currentBalance = request.getParameter("currentBalance");
        String rewardPoint = request.getParameter("rewardPoint");
        String dateOpened = request.getParameter("dateOpened");
        String dateClosed = request.getParameter("dateClosed");

        // try {
        //     paymentAccountDAO.CreatePaymentAccount(fullName, email, password, citizenId, phoneNumber, dateOfBirth, address, pinNumber);
        //     request.setAttribute("successMessage", "The customer has been added successfully.");

        // } catch (HandleException e) {
        //     request.setAttribute("errorMessage", e.getMessage());
        // }

    }

    protected void updatePaymentAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<PaymentAccount> paymentAccounts = paymentAccountDAO.findAllPaymentAccount();

        request.setAttribute("paymentAccounts", paymentAccounts);
    }

}
