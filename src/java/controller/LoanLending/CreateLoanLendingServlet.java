package controller.LoanLending;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import DAO.InterestRateDAO;
import DAO.LoanLendingDAO;
import business.LoanLending;
import business.Customer;
import business.InterestRate;
import Exception.HandleException;

@WebServlet("/create-loan-lending")
public class CreateLoanLendingServlet extends HttpServlet {

    LoanLendingDAO loanLendingDAO = new LoanLendingDAO();
    InterestRateDAO interestRateDAO = new InterestRateDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");

        if (action == null) {
            action = "join";
        }
        String url = "/loanLending.jsp";
        if (action.equals("create")) {
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            String customerId = customer.getCustomerId();
            String accountNumber = request.getParameter("acNumber");
            String amount = request.getParameter("loanLending");
            String loanTitle = request.getParameter("typeOfLoan");
            InterestRate interestRate = interestRateDAO.findByLoanTitle(loanTitle);

            System.out.println("The value of amount is: " + amount);

            try {
                loanLendingDAO.CreateLoanLending(customer, accountNumber, interestRate.getLoanTitle(), interestRate.getTerm(), Integer.parseInt(amount), interestRate);
                request.setAttribute("successMessage", "Your loan account has been created successfully");
                List<LoanLending> loanLendings = loanLendingDAO.findLoanLendingByCusId(customerId);
                request.setAttribute("loanLendings", loanLendings);
            } catch (HandleException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
