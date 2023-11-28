package controller.PaymentAccount;

import business.PaymentAccount;
import business.Customer;
import DAO.PaymentAccountDAO;
import Exception.HandleException;
import java.io.*;
import java.util.List;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/create-account")
public class CreateAccountServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        String url = "/account.jsp";
        if (action.equals("createCustom")) {
            String accountNumber = request.getParameter("acNumber");
            createPaymentAccount(request, response, customer,accountNumber);
        } else if (action.equals("createRandom")) {
            String accountNumber = generateAccountNumber();
            createPaymentAccount(request, response, customer,accountNumber);
        }

        List<PaymentAccount> paymentAccounts = paymentAccountDAO.findPaymentAccountByCusId(customerId);
        request.setAttribute("paymentAccounts", paymentAccounts);

        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    protected void createPaymentAccount(HttpServletRequest request, HttpServletResponse response, Customer customer, String accountNumber)
            throws ServletException, IOException {
        try {
            paymentAccountDAO.CreatePaymentAccount(customer, accountNumber);
            request.setAttribute("successMessage", "Your payment account has been created successfully");
        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

    protected String generateAccountNumber() {
        Random random = new Random();
        
        // Generate an 8-digit random number
        int randomPart = random.nextInt(90000000) + 10000000;

        
        String accountNumber = "89" + randomPart;

        return accountNumber;
    }
}
