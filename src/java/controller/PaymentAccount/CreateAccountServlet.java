package controller.PaymentAccount;

import business.PaymentAccount;
import business.Customer;
import DAO.PaymentAccountDAO;
import Exception.HandleException;
import java.io.*;
import java.util.List;
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

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        String url = "/account.jsp";
        if (action.equals("create")) {
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            String customerId = customer.getCustomerId();
            String accountNumber = request.getParameter("acNumber");
            String pinNumber = request.getParameter("pinNumber");

            try {
                paymentAccountDAO.CreatePaymentAccount(customer, accountNumber, pinNumber);

                request.setAttribute("successMessage", "Your payment account has been created successfully");
            } catch (HandleException e) {

                request.setAttribute("errorMessage", e.getMessage());
            }
            List<PaymentAccount> paymentAccounts = paymentAccountDAO.findPaymentAccountByCusId(customerId);
            request.setAttribute("paymentAccounts", paymentAccounts);

        }

        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
