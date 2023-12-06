package DAO;

import Exception.HandleException;
import business.Customer;
import business.InterestRate;
import business.SavingAccount;
import common.HashGenerator;
import common.MailSender;
import controller.User.SignupServlet;
import jakarta.mail.MessagingException;
import business.PaymentAccount;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;


import DAO.PaymentAccountDAO;
import DAO.InterestRateDAO;
import business.PaymentAccount;

public class SavingAccountDAO extends JpaDAO<SavingAccount> implements GenericDAO<SavingAccount> {

    @Override
    public SavingAccount create(SavingAccount t) {
        return super.create(t);
    }

    @Override
    public SavingAccount get(Object id) {
        return super.find(SavingAccount.class, id);
    }

    @Override
    public SavingAccount update(SavingAccount t) {
        return super.update(t);
    }

    @Override
    public void delete(Object id) {
        super.delete(SavingAccount.class, id);

    }

    @Override
    public List<SavingAccount> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

    public List<SavingAccount> findAllSavingAccount() {

        List<SavingAccount> result = super.findWithNamedQuery("SELECT sa FROM SavingAccount sa");

        if (!result.isEmpty()) {
            return result;
        }

        return null;
    }

    public List<SavingAccount> findSavingAccountByPayId(String paymentAccountId) {

        List<SavingAccount> savingAccountList = super.findWithNamedQuery(
                "SELECT sa FROM SavingAccount sa WHERE sa.paymentAccount.paymentAccountId = :paymentAccountId",
                "paymentAccountId",
                paymentAccountId);
        if (!savingAccountList.isEmpty()) {
            return savingAccountList;
        }

        return null;
    }
    
    public SavingAccount findBySavingId(String savingId) {

        List<SavingAccount> result = super.findWithNamedQuery("SELECT sa FROM SavingAccount sa WHERE sa.savingAccountId = :savingId",
                "savingId", savingId);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public SavingAccount findExistingSavingAccount(String accountNumber) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("accountNumber", accountNumber);

        List<SavingAccount> savingAccountList = super.findWithNamedQuery(
                "SELECT sa FROM SavingAccount sa WHERE sa.paymentAccount.paymentAccountId = :accountNumber",
                parameters);

        if (!savingAccountList.isEmpty()) {
            return savingAccountList.get(0);
        }

        return null;
    }

    public SavingAccount CreateSavingAccount(Customer customer, String accountNumber, String accountType, int term,
            Double amount, InterestRate interestRate) throws HandleException {

        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        SavingAccount savingAccountEntity = new SavingAccount();
        // SavingAccount existingSavingAccount = findExistingSavingAccount(customer.getCustomerId(), accountNumber);
        PaymentAccount paymentAccount = paymentAccountDAO.findExistingPaymentAccount(accountNumber);
        if (amount < 1000000) {
            throw new HandleException("The Saving Amount need to be more than 1000000 VND", 409);
        } else if (accountNumber == null || accountNumber.isEmpty() || accountType == null || accountType.isEmpty()) {
            throw new HandleException("Please fill in the form", 409);
        } else if (interestRate == null) {
            throw new HandleException("Something went wrong", 409);
        } else if (amount > paymentAccount.getCurrentBalence()) {
            throw new HandleException("The Saving Amount must be equal to or larger than your Current Balance", 409);
        } else {
            LocalDate time = LocalDate.now();
            savingAccountEntity.setSavingAccountId(generateUniqueId());
            savingAccountEntity.setAccountNumber(accountNumber);
            savingAccountEntity.setAccountStatus("Active");
            savingAccountEntity.setAccountType(accountType);
            savingAccountEntity.setDateOpened(time);
            savingAccountEntity.setDateClosed(time.plusMonths(term));
            savingAccountEntity.setMinBalance(1000000);
            savingAccountEntity.setSavingCurrentAmount(amount);
            savingAccountEntity.setSavingInitialAmount(amount);
            savingAccountEntity.setPaymentAccount(paymentAccount);
            savingAccountEntity.setInterestRate(interestRate);
            paymentAccount.setCurrentBalence(paymentAccount.getCurrentBalence() - amount);
            paymentAccountDAO.update(paymentAccount);
            create(savingAccountEntity);
        }
        return null;
    }

    public Map<String, Double> displayExpectedSaving(SavingAccount savingAccount, String strDate,InterestRate interestRate) {
        Double amount = savingAccount.getSavingInitialAmount();
        LocalDate checkDate = LocalDate.parse(strDate);
        // For calculating interest
        int numberOfDay = (checkDate.getMonthValue() - savingAccount.getDateOpened().getMonthValue());
        Map<String, Double> map = new HashMap<String, Double>();
        
        if (savingAccount.getDateOpened().isBefore(checkDate)){
            map = calculateInterest(amount, interestRate, savingAccount, checkDate);
            return map;
        }else if (savingAccount.getDateOpened().isAfter(checkDate)){
            map.put("monthlyTotal", 0.0);
            return map;
        }
        else if (savingAccount.getDateOpened().isEqual(checkDate)){
            map.put("monthlyTotal", 0.0);
            return map;
        }
        else{
            map.put("expectedTotal", 0.0);
            map.put("monthlyTotal", 0.0);
            return map;
        }
    }
    
    public Double calculateExpectedTotal(InterestRate rate, SavingAccount savingAccount) {
        Double expectedTotal = 0.0;
        Double interest = ((rate.getInterestRate() * 1.0) / 100) / 12;
        int term = rate.getTerm();
        expectedTotal = savingAccount.getSavingInitialAmount() * (Math.pow((1 + interest), term * 1.0));
        return expectedTotal;
    }
    
    public Map<String, Double> calculateInterest(Double amount, InterestRate rate, SavingAccount savingAccount, LocalDate checkDate) {
        Map<String, Double> result = new HashMap<>();
        Double interest = ((rate.getInterestRate() * 1.0) / 100) / 12;
        int term = rate.getTerm();
        // Calculate the number of months (considering partial months)
        YearMonth startYearMonth = YearMonth.from(savingAccount.getDateOpened());
        YearMonth endYearMonth = YearMonth.from(checkDate);
        long monthsDifference = ChronoUnit.MONTHS.between(startYearMonth, endYearMonth);
        // Round up to the nearest whole month
        int consTime = (int) Math.ceil(monthsDifference);
        Double monthlyTotal = 0.0;
        monthlyTotal = amount * (Math.pow((1 + interest), consTime * 1.0));
        // Format totalAmount to 2 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        monthlyTotal = Double.valueOf(decimalFormat.format(monthlyTotal));
        result.put("monthlyTotal", monthlyTotal);
        return result;
    }

    public SavingAccount updateCurrentSavingAccount(SavingAccount savingAccount, InterestRate rate){
        Double interest = ((rate.getInterestRate() * 1.0) / 100) / 12;  
        // Calculate the number of months (considering partial months)
        YearMonth startYearMonth = YearMonth.from(savingAccount.getDateOpened());
        YearMonth endYearMonth = null;
        if (!savingAccount.getDateClosed().isBefore(LocalDate.now())) {
            endYearMonth = YearMonth.from(LocalDate.now());
        }else{
            endYearMonth = YearMonth.from(savingAccount.getDateClosed());
            
        }
        long monthsDifference = ChronoUnit.MONTHS.between(startYearMonth, endYearMonth);
        // Round up to the nearest whole month
        int consTime = (int) Math.ceil(monthsDifference);
        Double currentAmount = savingAccount.getSavingInitialAmount() * (Math.pow((1 + interest), consTime * 1.0));
        savingAccount.setSavingCurrentAmount(currentAmount);
        update(savingAccount);
        return savingAccount;
    }

    public SavingAccount updateSavingAccount(String savingAccountId, String accNum, String accountStatus, String accType, String initial, String dateOpened, String dateClosed) throws HandleException {
        SavingAccount savingAccountEntityUpdate = new SavingAccount(); 
        SavingAccount existiSavingAccount = findBySavingId(savingAccountId);  
        InterestRateDAO interestRateDAO = new InterestRateDAO();
        InterestRate interestRate = interestRateDAO.findBySavingTitle(accType);

        savingAccountEntityUpdate.setSavingAccountId(savingAccountId);
        savingAccountEntityUpdate.setAccountNumber(accNum);
        savingAccountEntityUpdate.setAccountStatus(accountStatus);
        savingAccountEntityUpdate.setAccountType(accType);
        savingAccountEntityUpdate.setSavingInitialAmount(Double.parseDouble(initial));   
        savingAccountEntityUpdate.setSavingCurrentAmount(existiSavingAccount.getSavingCurrentAmount());
        savingAccountEntityUpdate.setDateOpened(LocalDate.parse(dateOpened));
        savingAccountEntityUpdate.setDateClosed(LocalDate.parse(dateClosed));
        savingAccountEntityUpdate.setMinBalance(1000000);
        savingAccountEntityUpdate.setPaymentAccount(existiSavingAccount.getPaymentAccount());
        savingAccountEntityUpdate.setInterestRate(interestRate);

        update(savingAccountEntityUpdate); 
        return savingAccountEntityUpdate;
    }

    public SavingAccount checkUpdateSavingAccount(String savingAccountId, String accNum, String accountStatus, String accType, String initial, String dateOpened, String dateClosed) throws HandleException {
        SavingAccount duplicatedAccNum = findByAccountNumber(accNum);

        if (duplicatedAccNum != null){
            if(duplicatedAccNum.getSavingAccountId().equals(savingAccountId)){
                updateSavingAccount(savingAccountId, accNum, accountStatus, accType, initial, dateOpened, dateClosed);
            }
            else{
                throw new HandleException("The saving account with number: " + accNum + " is already registered.", 409);
            }
        }
        else{
             updateSavingAccount(savingAccountId, accNum, accountStatus, accType, initial, dateOpened, dateClosed);
        }

        return null;
    }

    public SavingAccount findByAccountNumber(String accountNumber) {

        List<SavingAccount> result = super.findWithNamedQuery(
                "SELECT sa FROM SavingAccount sa WHERE sa.accountNumber = :accountNumber", "accountNumber",
                accountNumber);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public void Withdraw(SavingAccount savingAccount, Double currentSavingAmount) throws HandleException {

        SavingAccount savingAccountEntity = savingAccount;
        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
        PaymentAccount paymentAc = savingAccountEntity.getPaymentAccount();
        if (savingAccount.getDateClosed().compareTo(LocalDate.now()) > 0) {
            throw new HandleException("You have not yet reached the withdrawal date.", 409);
        } else if (paymentAc == null) {
            throw new HandleException("Please add your default payment account.", 409);
        } else {
            savingAccountEntity.setAccountStatus("Inactive");
            paymentAc.setCurrentBalence(paymentAc.getCurrentBalence() + currentSavingAmount);
            paymentAccountDAO.update(paymentAc);
            savingAccountDAO.update(savingAccountEntity);
        }
    }
}
