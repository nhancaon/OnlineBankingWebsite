package controller.SavingAccount;

import business.SavingAccount;
import business.Customer;
import DAO.SavingAccountDAO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/account-detail")
public class ShowAccountDetailServlet extends HttpServlet {

        SavingAccountDAO SavingAccountDAO = new SavingAccountDAO();
        SavingAccount savingAccount = new SavingAccount();

        @Override
        protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {

        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                ServletContext servletContext = getServletContext();

                String url = "/account.jsp";
                // Retrieve account number from the request parameters
                String accountNumber = request.getParameter("accountNumber");

                // Call findByAccountNumber in SavingAccountDAO
                savingAccount = SavingAccountDAO.findByAccountNumber(accountNumber);

                // Set the savingAccount as an attribute for accountdetail.jsp
                request.setAttribute("savingAccount", savingAccount);

                url = "/accountDetail.jsp";

                servletContext.getRequestDispatcher(url)
                                .forward(request, response);
        }
}
