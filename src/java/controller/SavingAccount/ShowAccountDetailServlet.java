package controller.SavingAccount;

import business.SavingAccount;
import DAO.SavingAccountDAO;
import business.InterestRate;
import java.io.*;
import java.time.LocalDate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/saving-detail")
public class ShowAccountDetailServlet extends HttpServlet {

    SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
    SavingAccount savingAccount = new SavingAccount();
    InterestRate interestRate = new InterestRate();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/savingDetail.jsp";
        String accountNumber = request.getParameter("accountNumber");

        Double totalAmount = 1.0;
        savingAccountDAO.calculateInterest(savingAccount.getSavingAmount(), 
                                           savingAccount.getInterestRate().getConsecutive(), 
                                           LocalDate.parse("2023-04-12"), 
                                           interestRate, 
                                           totalAmount);
        savingAccount.setSavingAmount(totalAmount);
        System.out.println(totalAmount);

        savingAccount = savingAccountDAO.findByAccountNumber(accountNumber);
        request.setAttribute("savingAccount", savingAccount);
        
        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }
}
