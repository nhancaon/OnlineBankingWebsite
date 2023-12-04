package controller.AdminDashboard;

import business.InterestRate;
import business.PaymentAccount;
import business.Customer;
import DAO.PaymentAccountDAO;
import DAO.CustomerDAO;
import Exception.HandleException;


import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/paymentAccount")
public class PaymentAccountServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
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
        String citizenId = request.getParameter("citizenId");
        Customer customer = customerDAO.findByCitizenId(citizenId);
        String accountNumber;

        if(customer == null){
            request.setAttribute("errorMessage", "The customer is not existed.");
        }
        else{
            if(!request.getParameter("accountNumber").isEmpty()){
                accountNumber = request.getParameter("accountNumber");
            }
            else{
                accountNumber = "random";
            }       

            try {
                paymentAccountDAO.CreatePaymentAccount(customer, accountNumber);
                request.setAttribute("successMessage", "The payment account has been added successfully.");
            } catch (HandleException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
    }

    protected void updatePaymentAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentAccountId = request.getParameter("paymentAccountIdUpdate");
        PaymentAccount paymentAccount = paymentAccountDAO.findByPaymentAccountId(paymentAccountId);

        String accountNumber;
        String accountStatus;
        String accountType;
        String balance;
        String rewardPoint;

        if(!request.getParameter("accountNumberUpdate").isEmpty()){
            accountNumber = request.getParameter("accountNumberUpdate");
        }else{
            accountNumber = paymentAccount.getAccountNumber();
        }
        if(request.getParameter("accountStatusUpdate") == null){ 
            accountStatus = paymentAccount.getAccountStatus();
        }else{
            accountStatus = request.getParameter("accountStatusUpdate");
        }
        if(!request.getParameter("accountTypeUpdate").isEmpty()){
            accountType = request.getParameter("accountTypeUpdate");
        }else{
            accountType = paymentAccount.getAccountType();
        }
        if(!request.getParameter("currentBalanceUpdate").isEmpty()){
            balance = request.getParameter("currentBalanceUpdate");
        }else{
            balance = String.valueOf(paymentAccount.getCurrentBalence());
        }
        if(!request.getParameter("rewardPointUpdate").isEmpty()){
            rewardPoint = request.getParameter("rewardPointUpdate");
        }else{
            rewardPoint = String.valueOf(paymentAccount.getRewardPoint());
        }

        try {
            paymentAccountDAO.checkUpdatePaymentAccount(paymentAccountId, accountNumber, accountStatus, accountType, balance, rewardPoint, paymentAccount.getCustomer().getCustomerId());
            request.setAttribute("successMessage", "The payment account has been updated successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

    public String formatCurrency(Double amount) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        // Change the decimal separator from '.' to ','
        if (currencyFormatter instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) currencyFormatter;
            decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("en", "US")));
        }
        String formattedAmount = currencyFormatter.format(amount);
        // Remove the '.00' at the end
        formattedAmount = formattedAmount.replaceAll("\\.00$", "");
        formattedAmount = formattedAmount.replace("$", "");
        
        return formattedAmount;
    }
}
