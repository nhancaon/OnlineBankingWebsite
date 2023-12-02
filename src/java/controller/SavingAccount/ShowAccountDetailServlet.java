package controller.SavingAccount;

import business.SavingAccount;
import DAO.InterestRateDAO;
import DAO.SavingAccountDAO;
import business.InterestRate;
import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    //    ServletContext servletContext = getServletContext();
    //    String url = "/savingDetail.jsp";       
    //    servletContext.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String url = "/savingDetail.jsp";
        String accountNumber = request.getParameter("accountNumber");     
        savingAccount = savingAccountDAO.findByAccountNumber(accountNumber);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String checkDate = request.getParameter("checkDate");
        String current = request.getParameter("checkDate");
        System.out.println("1111111111111");

        Map<String, Double> map = new HashMap<String, Double>();

        Double expectedTotal = 0.0;
        Double monthlyTotal = 0.0;
        Double initialAmount =  savingAccount.getSavingInitialAmount();
        System.out.println("initialAmount "+ initialAmount);

        if (checkDate == null){
            checkDate = currentDate.format(formatter);

            map = savingAccountDAO.displayExpectedSaving(accountNumber, checkDate);
            expectedTotal = map.get("expectedTotal");
            monthlyTotal = map.get("monthlyTotal");
        }
        else {
            map = savingAccountDAO.displayExpectedSaving(accountNumber, checkDate);
            expectedTotal = map.get("expectedTotal");
            monthlyTotal = map.get("monthlyTotal");
        }

        LocalDate dateCheckInitial = LocalDate.parse(current, formatter);
        System.out.println("dateCheckInitial: " + dateCheckInitial);

        // if(dateCheckInitial.getYear() > savingAccount.getDateOpened().getYear()){
        //     initialAmount = savingAccount.getSavingAmount();
        // }
        // else if (dateCheckInitial.getYear() == savingAccount.getDateOpened().getYear()){
        //     if (dateCheckInitial.getMonthValue() >= savingAccount.getDateOpened().getMonthValue()){
        //         initialAmount = savingAccount.getSavingAmount();
        //     }
        // }

        if(savingAccount.getDateOpened().isBefore(LocalDate.now())){
            savingAccount = savingAccountDAO.updateCurrentSavingAccount(accountNumber, savingAccount, monthlyTotal);
        }

        // Render to jsp
        String expectedAmount = String.valueOf(expectedTotal);
        String monthlyAmount = String.valueOf(monthlyTotal);
        String initialString = String.valueOf(initialAmount);
        
        request.setAttribute("expectedAmount", expectedAmount);
        request.setAttribute("monthlyAmount", monthlyAmount);
        request.setAttribute("initialString", initialString);
        request.setAttribute("savingAccount", savingAccount); 
        servletContext.getRequestDispatcher(url).forward(request, response);
    }
}
