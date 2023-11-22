package controller.SavingAccount;

import DAO.InterestRateDAO;
import business.SavingAccount;
import business.Customer;
import business.InterestRate;
import DAO.SavingAccountDAO;
import Exception.HandleException;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/with-draw")
public class WithdrawServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
        SavingAccount savingAccount = null;
        String action = request.getParameter("action");

        if (action == null) {
            action = "join";
        }
        String url = "/savingDetail.jsp";
        if (action.equals("withdraw")) {
            String savingAccountId = request.getParameter("accountSavingId");
            savingAccount = savingAccountDAO.find(SavingAccount.class, savingAccountId);
            request.setAttribute("savingAccount", savingAccount);
            Double expectedAmount = Double.valueOf(request.getParameter("expectedAmount"));
            try {
                savingAccountDAO.Withdraw(savingAccount,expectedAmount.intValue());
                request.setAttribute("successMessage", "Withdraw successfully");
            } catch (HandleException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }
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
