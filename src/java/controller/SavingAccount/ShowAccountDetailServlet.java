package controller.SavingAccount;

import business.SavingAccount;
import DAO.InterestRateDAO;
import DAO.SavingAccountDAO;
import business.InterestRate;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
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

        String accountID = request.getParameter("accountId");
        savingAccount = savingAccountDAO.findBySavingId(accountID);
        interestRate = interestRateDAO.findInterestRateByInterestId(savingAccount.getInterestRate().getInterestId());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String checkDate = request.getParameter("checkDate");

        //Calculate expectedTotal
        Double expectedTotal = savingAccountDAO.calculateExpectedTotal(interestRate, savingAccount);

        Map<String, Double> map = new HashMap<String, Double>();
        Double monthlyTotal = 0.0;
        Double initialAmount = savingAccount.getSavingInitialAmount();
        Double currentSavingAmount = savingAccount.getSavingCurrentAmount();

        if (checkDate == null) {
            if (!savingAccount.getDateClosed().isBefore(LocalDate.now())) {
                checkDate = currentDate.format(formatter);
            } else {
                checkDate = savingAccount.getDateClosed().format(formatter);
            }
            map = savingAccountDAO.displayExpectedSaving(savingAccount, checkDate, interestRate);
            monthlyTotal = map.get("monthlyTotal");
        } else {
            map = savingAccountDAO.displayExpectedSaving(savingAccount, checkDate, interestRate);
            monthlyTotal = map.get("monthlyTotal");
        }
        if (savingAccount.getDateOpened().isAfter(LocalDate.parse(checkDate))) {
            initialAmount = 0.0;
            currentSavingAmount = 0.0;
        } else if (savingAccount.getDateOpened().isBefore(LocalDate.now())) {
            savingAccount = savingAccountDAO.updateCurrentSavingAccount(savingAccount, interestRate);
        } else {
            if (savingAccount.getDateClosed().isAfter(LocalDate.now())) {
                checkDate = savingAccount.getDateClosed().format(formatter);
            }
            initialAmount = savingAccount.getSavingInitialAmount();
            currentSavingAmount = savingAccount.getSavingInitialAmount();

        }
        request.setAttribute("expectedAmount", expectedTotal);
        request.setAttribute("monthlyAmount", monthlyTotal);
        request.setAttribute("initialString", initialAmount);
        request.setAttribute("currentSavingAmount", currentSavingAmount);
        request.setAttribute("savingAccount", savingAccount);
        String url = "/savingDetail.jsp";
        servletContext.getRequestDispatcher(url).forward(request, response);
    }
}
