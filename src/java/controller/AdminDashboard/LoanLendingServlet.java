package controller.AdminDashboard;
import business.LoanLending;
import DAO.LoanLendingDAO;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/loan")
public class LoanLendingServlet extends HttpServlet {

    LoanLendingDAO loanLendingDAO = new LoanLendingDAO();

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
            case "show-loan" -> this.showLoan(request, response);
            default -> {}
        }
        url = "/admin-dashboard/loanLending.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showLoan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<LoanLending> loanLendings = loanLendingDAO.findAllLoanLending();

        request.setAttribute("loanLendings", loanLendings);
    }

}
