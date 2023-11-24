package controller.Beneficiary;

import business.PaymentAccount;
import business.Customer;
import DAO.BeneficiaryDAO;
import Exception.HandleException;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "CreateBeneficiaryServlet", urlPatterns = {"/CreateBeneficiary"})
public class CreateBeneficiaryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        
        String url = "/beneficiary.jsp";
        if (action.equals("create")) {
            //     HttpSession session = request.getSession();
            //     Customer customer = (Customer) session.getAttribute("customer");
            //     String customerId = customer.getCustomerId();
            String accountNumber = request.getParameter("accountNumber");
            String nickName = request.getParameter("nickName");

            //     try {
            //         paymentAccountDAO.CreatePaymentAccount(customer, accountNumber);
            //         request.setAttribute("successMessage", "Your payment account has been created successfully");
            //     } catch (HandleException e) {
            //         request.setAttribute("errorMessage", e.getMessage());
            //     }
            //     List<PaymentAccount> paymentAccounts = paymentAccountDAO.findPaymentAccountByCusId(customerId);
            //     request.setAttribute("paymentAccounts", paymentAccounts);
        }

        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }
}
