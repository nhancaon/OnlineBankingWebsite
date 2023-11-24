package controller.Transfer;

import DAO.PaymentAccountDAO;
import DAO.TransactionDAO;
import Exception.HandleException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import business.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransferServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
    TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/transfer.jsp";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "return";  // default action
        }
        if (action.equals("check")) {
            url = "/profile.jsp";
        } else // perform action and set URL to appropriate page
        if (action.equals("return")) {
            url = "/transfer.jsp";
        } else {
            HttpSession session = request.getSession();
            if (action.equals("add")) {
                String Number = request.getParameter("acNumber");
                String Name = request.getParameter("acName");
                String Amount = request.getParameter("acAmount");
                String Remark = request.getParameter("transRemark");
                Customer customer = (Customer) session.getAttribute("customer");
                PaymentAccount sender = null;
                sender = paymentAccountDAO.findDefaultPaymentAccount(customer.getCustomerId());
                if (sender != null) {
                    session.setAttribute("sender", sender);
                }
                PaymentAccount receiver = paymentAccountDAO.findExistingPaymentAccount(Number);
                if (receiver != null) {
                    session.setAttribute("receiver", receiver);
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm:ss");
                LocalDateTime time = LocalDateTime.now();
                String timeStr = time.format(formatter);
                session.setAttribute("timeStr", timeStr);
                session.setAttribute("Amount", Amount);
                session.setAttribute("Name", Name);
                session.setAttribute("Remark", Remark);
                session.setAttribute("receiver", receiver);
                session.setAttribute("time", time);
                
                try {
                    transactionDAO.checkTransaction(sender, receiver, Remark, Double.valueOf(Amount), time);
                    request.setAttribute("successMessage", "Transfer successfully");
                    url="/confirm.jsp";
                } catch (HandleException e) {
                    url="/transfer.jsp";
                    request.setAttribute("errorMessage", e.getMessage());
                    
                }
            } else if (action.equals("confirm")) {
                String Amount = (String) session.getAttribute("Amount");
                String Remark = (String) session.getAttribute("Remark");
                LocalDateTime time = (LocalDateTime) session.getAttribute("time");
                PaymentAccount sender = (PaymentAccount) session.getAttribute("sender");
                PaymentAccount receiver = (PaymentAccount) session.getAttribute("receiver");
                int amount = Integer.parseInt(Amount);
                try {
                    transactionDAO.createTransaction(sender, receiver, Remark, amount, time);
                    request.setAttribute("successMessage", "Transfer successfully");
                } catch (HandleException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                }
                url = "/success.jsp";
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
