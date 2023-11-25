package controller.LoanLending;

import business.LoanLending;
import DAO.LoanLendingDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/loan-detail")
public class ShowLoanLendingDetailServlet extends HttpServlet {

    LoanLendingDAO loanLendingDAO = new LoanLendingDAO();
    LoanLending loanLending = new LoanLending();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/loanDetail.jsp";
        String accountNumber = request.getParameter("accountNumber");
        loanLending = loanLendingDAO.findByAccountNumber(accountNumber);
        request.setAttribute("loanLending", loanLending);
        
        servletContext.getRequestDispatcher(url).forward(request, response);
    }
}
