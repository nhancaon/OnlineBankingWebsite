package controller.AdminDashboard;

import business.Customer;
import business.Transaction;
import DAO.TransactionDAO;
import Exception.HandleException;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin-dashboard/transaction")
public class TransactionServlet extends HttpServlet {

    TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // default action
        }

        String url = "/admin-dashboard/";
        switch (action) {
            case "update-transaction" -> {
                this.updateTransaction(request, response);
                this.showTransaction(request, response);
            }
            default -> {
            }
        }
        url = "/admin-dashboard/transaction.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    protected void showTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transaction> transactions = transactionDAO.findAllTransaction();
        request.setAttribute("transactions", transactions);
    }

    protected void addTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    protected void updateTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String transactionId = request.getParameter("transactionIdUpdate");
        Transaction transaction = transactionDAO.findByTransactionId(transactionId);

        String remark;

        if(!request.getParameter("transactionRemarkUpdate").isEmpty()){
            remark = request.getParameter("transactionRemarkUpdate");
        }else{
            remark = transaction.getTransactionRemark();
        }

        try {
            transactionDAO.updateTransaction(transactionId, remark);
            request.setAttribute("successMessage", "The transaction has been updated successfully.");

        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }
    }

}
