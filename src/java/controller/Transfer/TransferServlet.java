package controller.Transfer;

import DAO.PaymentAccountDAO;
import DAO.TransactionDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import business.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
                String message;
                if (Number == null || Amount == null || Remark == null || Name == null || Name.isEmpty()
                        || Number.isEmpty() || Amount.isEmpty() || Remark.isEmpty()) {
                    message = "Please fill out all information.";
                    url = "/transfer.jsp";
                } else {
                    message = "Here is the information that you enter:";
                    url = "/confirm.jsp";
                }
                Customer customer = (Customer) session.getAttribute("customer");
                PaymentAccount sender = null;
                sender = paymentAccountDAO.findDefaultPaymentAccount(customer.getCustomerId());
                if (sender == null) {
                    message="Please add your payment account before transfer";
                    url="/transfer.jsp";
                } else {
                    session.setAttribute("sender", sender);
                }
                PaymentAccount receiver = paymentAccountDAO.findByAccountNumber(Number);
                if (receiver == null) {
                    message = "This account isn't exist";
                    url = "/transfer.jsp";
                }else{
                    session.setAttribute("receiver", receiver);
                }
                if(sender.getCurrentBalence() < Double.valueOf(Amount)){
                    message="Your account is not enough";
                    url="/transfer.jsp";
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm:ss");
                LocalDateTime time =LocalDateTime.now();
                String timeStr = time.format(formatter);
                session.setAttribute("timeStr", timeStr);
                session.setAttribute("Amount", Amount);
                session.setAttribute("Remark", Remark);
                session.setAttribute("receiver", receiver);
                session.setAttribute("time", time);
                session.setAttribute("message", message);
            } else if (action.equals("confirm")) {
                String Amount = (String) session.getAttribute("Amount");
                String Remark = (String) session.getAttribute("Remark");
                LocalDateTime time=(LocalDateTime) session.getAttribute("time");
                PaymentAccount sender = (PaymentAccount) session.getAttribute("sender");
                PaymentAccount receiver = (PaymentAccount) session.getAttribute("receiver");
                Double amount = Double.valueOf(Amount);
                System.out.println(amount);
                System.out.println(Remark);
                transactionDAO.createTransaction(sender, receiver, Remark, amount,time);
                List<Transaction> transactionList=transactionDAO.findTransactionsByCusId(sender.getPaymentAccountId());
                session.setAttribute("transactionList", transactionList);
                url = "/success.jsp";
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
