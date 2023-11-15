package controller.PaymentAccount;

import business.PaymentAccount;
import business.Customer;
import DAO.PaymentAccountDAO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ShowAccount")
public class ShowAccountServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();
        List<PaymentAccount> paymentAccounts = paymentAccountDAO.findPaymentAccountByCusId(customerId);

        System.out.println(paymentAccounts);
        request.setAttribute("paymentAccounts", paymentAccounts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
        dispatcher.forward(request, response);
        response.sendRedirect("account.jsp");
    }
}
