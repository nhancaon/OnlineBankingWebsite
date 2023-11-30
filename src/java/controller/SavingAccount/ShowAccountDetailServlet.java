package controller.SavingAccount;

import business.SavingAccount;
import DAO.InterestRateDAO;
import DAO.SavingAccountDAO;
import business.InterestRate;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ejb.Local;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/saving-detail")
public class ShowAccountDetailServlet extends HttpServlet {

    SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
    SavingAccount savingAccount = new SavingAccount();
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
//        ServletContext servletContext = getServletContext();
//
//        String url = "/savingDetail.jsp";
//        
//        
//        
//        servletContext.getRequestDispatcher(url).forward(request, response);
    
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        String url = "/savingDetail.jsp";
        String accountNumber = request.getParameter("accountNumber");     
        String checkDate = request.getParameter("checkDate");
                System.out.println(checkDate);
        if (checkDate == null){
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            checkDate = currentDate.format(formatter);
        }else{
            savingAccount = savingAccountDAO.updateSavingAccount(accountNumber, checkDate);
        }
        request.setAttribute("savingAccount", savingAccount);
        
        servletContext.getRequestDispatcher(url).forward(request, response);
    }


}
