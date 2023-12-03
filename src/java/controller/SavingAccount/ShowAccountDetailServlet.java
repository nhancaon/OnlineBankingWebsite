package controller.SavingAccount;

import business.SavingAccount;
import DAO.InterestRateDAO;
import DAO.SavingAccountDAO;
import business.InterestRate;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
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
    InterestRateDAO interestRateDAO = new InterestRateDAO();
    InterestRate interestRate = new InterestRate();
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String url = "/savingDetail.jsp";
        String accountNumber = request.getParameter("accountNumber");     
        savingAccount = savingAccountDAO.findByAccountNumber(accountNumber);
        interestRate = interestRateDAO.findInterestRateByInterestId(savingAccount.getInterestRate().getInterestId());

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String checkDate = request.getParameter("checkDate");

        Map<String, Double> map = new HashMap<String, Double>();

        Double expectedTotal = 0.0;
        Double monthlyTotal = 0.0;
        Double initialAmount =  savingAccount.getSavingInitialAmount();
        Double currentSavingAmount = savingAccount.getSavingCurrentAmount();

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

        if (savingAccount.getDateOpened().isAfter(LocalDate.parse(checkDate))){
            initialAmount = 0.0;
            currentSavingAmount = 0.0;
        }

        if(savingAccount.getDateOpened().isBefore(LocalDate.now())){
            savingAccount = savingAccountDAO.updateCurrentSavingAccount(accountNumber, savingAccount, interestRate);
        }

        // // Format expectedAmount and monthlyAmount as currency strings
        // DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        // decimalFormat.setCurrency(Currency.getInstance(Locale.getDefault()));
        // decimalFormat.setNegativePrefix(""); // Remove negative sign if needed
        // decimalFormat.setNegativeSuffix("");

        // String expectedAmount = String.valueOf(expectedTotal);
        // String monthlyAmount = String.valueOf(monthlyTotal);
        // String initialString = String.valueOf(initialAmount);
        
        request.setAttribute("expectedAmount", expectedTotal);
        request.setAttribute("monthlyAmount", monthlyTotal);
        request.setAttribute("initialString", initialAmount);
        request.setAttribute("currentSavingAmount", currentSavingAmount);
        request.setAttribute("savingAccount", savingAccount); 
        servletContext.getRequestDispatcher(url).forward(request, response);
    }
}
