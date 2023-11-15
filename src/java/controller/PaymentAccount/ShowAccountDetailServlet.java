package controller.PaymentAccount;

import business.PaymentAccount;
import business.Customer;
import DAO.PaymentAccountDAO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ShowAccountDetail")
public class ShowAccountDetailServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
    PaymentAccount paymentAccount = new PaymentAccount();
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
