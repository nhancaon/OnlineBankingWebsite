package controller.SavingAccount;

import DAO.InterestRateDAO;
import DAO.PaymentAccountDAO;
import business.SavingAccount;
import business.Customer;
import business.InterestRate;
import DAO.SavingAccountDAO;
import Exception.HandleException;
import business.PaymentAccount;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/create-saving-account")
public class CreateAccountServlet extends HttpServlet {

    InterestRateDAO interestRateDAO = new InterestRateDAO();
    SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String action = request.getParameter("action");
        String url = "/savingAccount.jsp";

        if (action == null || action.isEmpty()) {
            action = "reload";
        }
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        PaymentAccount DefaultAc = paymentAccountDAO.findDefaultPaymentAccount(customer.getCustomerId());
        if (action.equals("create")) {
            String accountNumber = request.getParameter("acNumber");
            String amount = request.getParameter("savingAmount");
            String savingTitle = request.getParameter("typeOfSaving");
            InterestRate interestRate = interestRateDAO.findBySavingTitle(savingTitle);

            try {
                savingAccountDAO.CreateSavingAccount(customer, accountNumber, interestRate.getSavingTitle(), interestRate.getTerm(), Double.valueOf(amount), interestRate);
                request.setAttribute("successMessage", "Your saving account has been created successfully");
            } catch (HandleException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        //Find saving accounts
        List<SavingAccount> savingAccounts = savingAccountDAO.findSavingAccountByPayId(DefaultAc.getPaymentAccountId());
        request.setAttribute("savingAccounts", savingAccounts);
        //Find paymemt accounts
        List<PaymentAccount> paymentAccounts = paymentAccountDAO.findPaymentAccountByCusId(customer.getCustomerId());
        request.setAttribute("paymentAccounts", paymentAccounts);
        //Find Interest rates
        List<InterestRate> interestRates = interestRateDAO.listAll();
        request.setAttribute("interestRates", interestRates);
        servletContext.getRequestDispatcher(url).forward(request, response);
    }
}
