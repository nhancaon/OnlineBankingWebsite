package controller.AdminDashboard;

import business.SavingAccount;
import DAO.SavingAccountDAO;
import Exception.HandleException;
import business.Customer;
import business.InterestRate;
import DAO.CustomerDAO;
import business.PaymentAccount;
import DAO.PaymentAccountDAO;
import DAO.InterestRateDAO;


import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/savingAccount")
public class SavingAccountServlet extends HttpServlet {

    SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
    InterestRateDAO interestRateDAO = new InterestRateDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        if ("get-citizen-id".equals(action)) {
            // Handle AJAX request for citizenId
            String citizenId = request.getParameter("citizenId");
            Customer customer = customerDAO.findByCitizenId(citizenId);
            List<PaymentAccount> paymentAccounts = paymentAccountDAO.findPaymentAccountByCusId(customer.getCustomerId());
            
            String accountNumber = request.getParameter("accountNumber");
            if (customer != null) {         
                if (accountNumber != null) {
                    PaymentAccount paymentAccByAccNum = paymentAccountDAO.findByPaymentAccountNumber(accountNumber);
                    if (paymentAccByAccNum != null) {
                        // System.out.println(paymentAccByAccNum.getPaymentAccountId());  
                    } 
                }            
            } 
            
            // Find Interest rates
            List<InterestRate> interestRates = interestRateDAO.listAll();
            String savingTitle = request.getParameter("savingTitle");
            InterestRate interestRateCheck = interestRateDAO.findBySavingTitle(savingTitle);
 
            if (interestRateCheck != null) {
                String rate = String.valueOf(interestRateCheck.getInterestRate());
                System.out.println("rate: " + rate);
            } else {
                System.out.println("Interest rate not found for savingTitle: " + savingTitle);
                // You might want to write an error message or take appropriate action
            }

            // Send the response back to the client
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            // Write customer ID to the response
            response.getWriter().write("CUSTOMER_ID:" + (customer != null ? customer.getCustomerId() : ""));
            // Write customer name to the response
            response.getWriter().write("\nCUSTOMER_NAME:" + (customer != null ? customer.getName() : ""));
            
            // Separate each account number in the response
            for (PaymentAccount paymentAccount : paymentAccounts) {
                response.getWriter().write("\nACCOUNT_NUMBER:" + paymentAccount.getAccountNumber());
            }
            for (InterestRate interestRate : interestRates) {
                response.getWriter().write("\nINTEREST_RATE_TITLE:" + interestRate.getSavingTitle() + " | " + interestRate.getInterestRate() + "%");
            }
            return;
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "add-savingAccount" -> {
                this.addSavingAccount(request, response);
                this.showSavingAccount(request, response);
            }
            case "update-savingAccount" -> {
                this.updateSavingAccount(request, response);
                this.showSavingAccount(request, response);
            }
            case "delete" -> {
                this.deleteSavingAccount(request, response);
                this.showSavingAccount(request, response);
            }
            default -> {
            }
        }
        url = "/admin-dashboard/savingAccount.jsp";
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
            case "show-savingAccount" ->
                this.showSavingAccount(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/savingAccount.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showSavingAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SavingAccount> savingAccounts = savingAccountDAO.findAllSavingAccount();
        request.setAttribute("savingAccounts", savingAccounts);
    }

    protected void addSavingAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = customerDAO.findByCitizenId(request.getParameter("citizenId"));

        // Assuming "string" is the parameter name
        String savingTitle = request.getParameter("savingTitle");

        if (savingTitle != null) {
            // Split the string by the space character
            String[] parts = savingTitle.split("\\s+");

            // Get the first part (index 0) if there is any
            String firstPart = parts.length > 0 ? parts[0] : "";

            String accountType = firstPart;
            InterestRate interestRate = interestRateDAO.findBySavingTitle(accountType);
            String accountNumber = request.getParameter("accountNumber");
            String initialAmount = request.getParameter("initialAmount");
            try {
                savingAccountDAO.CreateSavingAccount(customer, accountNumber, interestRate.getSavingTitle(), interestRate.getTerm(), Double.valueOf(initialAmount), interestRate);
                request.setAttribute("successMessage", "The interest rate has been added successfully.");

            } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
            }
        }
    }

    protected void updateSavingAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savingAccountId = request.getParameter("savingAccountIdUpdate");
        SavingAccount savingAccount = savingAccountDAO.findBySavingId(savingAccountId);

        String accountStatus, accType, initial, dateOpened, dateClosed;

        if (!request.getParameter("accountStatusUpdate").isEmpty()) {
            accountStatus = request.getParameter("accountStatusUpdate");
        } else {
            accountStatus = savingAccount.getAccountStatus();
        }
        if (!request.getParameter("accountTypeUpdate").isEmpty()) {
            accType = request.getParameter("accountTypeUpdate");
        } else {
            accType = savingAccount.getAccountType();
        }
        if (!request.getParameter("initialAmountUpdate").isEmpty()) {
            initial = request.getParameter("initialAmountUpdate");
        } else {
            initial = String.valueOf(savingAccount.getSavingInitialAmount());
        }
        if (!request.getParameter("dateOpenedUpdate").isEmpty()) {
            dateOpened = request.getParameter("dateOpenedUpdate");
        } else {
            dateOpened = String.valueOf(savingAccount.getDateOpened());
        }
        if (!request.getParameter("dateClosedUpdate").isEmpty()) {
            dateClosed = request.getParameter("dateClosedUpdate");
        } else {
            dateClosed = String.valueOf(savingAccount.getDateClosed());
        }

        try {
            savingAccountDAO.checkUpdateSavingAccount(savingAccountId, savingAccount.getAccountNumber(), accountStatus, accType, initial, dateOpened, dateClosed);
            request.setAttribute("successMessage", "The saving account has been updated successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

    protected void deleteSavingAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savingAccountId = request.getParameter("savingAccountId");
        savingAccountDAO.delete(savingAccountId);
    }
}
