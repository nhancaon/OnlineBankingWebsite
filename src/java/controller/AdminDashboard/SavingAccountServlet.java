package controller.AdminDashboard;

import business.SavingAccount;
import DAO.SavingAccountDAO;
import business.Customer;
import DAO.CustomerDAO;
import business.PaymentAccount;
import DAO.PaymentAccountDAO;

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

            if (customer != null) {
                System.out.println("citizenId " + citizenId);
                System.out.println("customer " + customer.getCustomerId());
                for (int i = 0; i < paymentAccounts.size(); i++) {
                    System.out.println("accountNumber[" + i + "] " + paymentAccounts.get(i).getAccountNumber());
                }
            }
            // // Concatenate account numbers into a comma-separated string
            // StringBuilder accountNumbersString = new StringBuilder();
            // for (int i = 0; i < paymentAccounts.size(); i++) {
            //     accountNumbersString.append(paymentAccounts.get(i).getAccountNumber());
            //     if (i < paymentAccounts.size() - 1) {
            //         accountNumbersString.append(",");
            //     }
            // }

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
            return;
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "add-savingAccount" -> {
                this.addSavingAccount(request, response);
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
        String accountNumber = request.getParameter("accountNumber");
        String accountStatus = request.getParameter("accountStatus");
        String accountType = request.getParameter("accountType");
        String savingAmount = request.getParameter("savingAmount");
        String dateOpened = request.getParameter("dateOpened");
        String dateClosed = request.getParameter("dateClosed");

    }

    protected void updateSavingAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void deleteSavingAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savingAccountId = request.getParameter("savingAccountId");
        savingAccountDAO.delete(savingAccountId);
    }

}
