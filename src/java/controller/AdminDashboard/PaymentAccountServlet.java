package controller.AdminDashboard;
import business.PaymentAccount;
import DAO.PaymentAccountDAO;

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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "show-paymentAccount" -> this.showPaymentAccount(request, response);
            default -> {}
        }
        url = "/admin-dashboard/paymentAccount.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showPaymentAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<PaymentAccount> paymentAccounts = paymentAccountDAO.findAllPaymentAccount();

        request.setAttribute("paymentAccounts", paymentAccounts);
    }

}
