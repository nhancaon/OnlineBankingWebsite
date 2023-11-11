package control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import business.*;
import data.*;
import java.time.LocalDateTime;

public class TransferServlet extends HttpServlet {

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

        // perform action and set URL to appropriate page
        if (action.equals("return")) {
            url = "/transfer.jsp";
//        } else if (action.equals("confirm")) {
//            //TransactionDB.createTransaction(sender.getCurrentAccountId(), receiver.getCurrentAccountId(), transac.getTransactionRemake(), transac.getAmount());
//            TransactionDB.createTransaction(123, 234, "hehe", 50.0);
//            url = "/success.jsp";
        } else {
            HttpSession session = request.getSession();
            if (action.equals("add")) {

                // get parameters from the request
                String Number = request.getParameter("acNumber");
                String Name = request.getParameter("acName");
                String Amount = request.getParameter("acAmount");
                String Remark = request.getParameter("transRemark");
                System.out.println("Number");
                String message;
                if (Number == null || Amount == null || Remark == null || Name == null || Name.isEmpty()
                        || Number.isEmpty() || Amount.isEmpty() || Remark.isEmpty()) {
                    message = "Please fill out all information.";
                    url = "/transfer.jsp";
                } else {
                    message = "Here is the information that you enter:";
                    url = "/confirm.jsp";
                }

                // store data in User object
                Transaction transac = new Transaction();
                transac.setAmount(Double.valueOf(Amount));
                transac.setTransactionDate(LocalDateTime.now());
                transac.setTransactionRemake(Remark);

                CurrentAccount sender = CurrentAccountDB.findByAccountNumber("123");
                CurrentAccount receiver = CurrentAccountDB.findByAccountNumber(Number);
                if (receiver == null) {
                    message = "This account isn't exist";
                    url = "/transfer.jsp";
                }
                session.setAttribute("Number", Number);
                session.setAttribute("Amount", Amount);
                session.setAttribute("Remark", Remark);
                session.setAttribute("transac", transac);
                session.setAttribute("sender", sender);
                session.setAttribute("receiver", receiver);
                session.setAttribute("message", message);
            } else if (action.equals("confirm")) {
                String Number = (String) session.getAttribute("Number");
                String Amount = (String) session.getAttribute("Amount");
                String Remark = (String) session.getAttribute("Remark");
                Double amount=Double.valueOf(Amount);
                System.out.println(Number);
                System.out.println(amount);
                System.out.println(Remark);
                TransactionDB.createTransaction("123", Number,Remark, amount);
                url = "/success.jsp";
            }

        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
