package controller.PaymentAccount;

import business.PaymentAccount;
import business.Transaction;
import DAO.PaymentAccountDAO;
import DAO.TransactionDAO;
import business.Customer;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AccountDetail")
public class ShowAccountDetailServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
    PaymentAccount paymentAccount = new PaymentAccount();
    TransactionDAO transactionDAO = new TransactionDAO();
    Transaction transaction = new Transaction();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/accountDetail.jsp";

        String accountNumber = request.getParameter("accountNumb");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();
        paymentAccountDAO.setDefaultPaymentAccount(customerId, accountNumber);

        showAccountDetail(request, response, accountNumber);

        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/account.jsp";

        // Retrieve account number from the request parameters
        String accountNumber = request.getParameter("accountNumber");
        showAccountDetail(request, response, accountNumber);
        url = "/accountDetail.jsp";
        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

    protected void showAccountDetail(HttpServletRequest request, HttpServletResponse response, String accountNumber)
            throws ServletException, IOException {

        // Call findByAccountNumber in PaymentAccountDAO
        paymentAccount = paymentAccountDAO.findExistingPaymentAccount(accountNumber);

        List<Transaction> transactionList = transactionDAO.findTransactionOfPaymentAccountId(paymentAccount.getPaymentAccountId());

        // Set the paymentAccount as an attribute for accountdetail.jsp
        request.setAttribute("paymentAccount", paymentAccount);
        request.setAttribute("transactionList", transactionList);

    }
}
