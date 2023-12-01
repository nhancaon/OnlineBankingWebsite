package controller.AdminDashboard;

import business.Transaction;
import DAO.TransactionDAO;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/transaction")
public class TransactionServlet extends HttpServlet {

    TransactionDAO transactionDAO = new TransactionDAO();

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
            case "show-transaction" ->
                this.showTransaction(request, response);
            default -> {
            }
        }
        url = "/admin-dashboard/transaction.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    protected void showTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Transaction> transactions = transactionDAO.findAllTransaction();
        System.out.println(transactions);
        request.setAttribute("transactions", transactions);
    }

    protected void addTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
      protected void updateTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
