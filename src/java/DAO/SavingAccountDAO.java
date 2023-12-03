package DAO;

import Exception.HandleException;
import business.Customer;
import business.InterestRate;
import business.SavingAccount;
import business.PaymentAccount;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import java.text.DecimalFormat;
import DAO.PaymentAccountDAO;

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
            Double amount, InterestRate interestRate, Boolean cons) throws HandleException {

        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        SavingAccount savingAccountEntity = new SavingAccount();
        // SavingAccount existingSavingAccount = findExistingSavingAccount(customer.getCustomerId(), accountNumber);
        PaymentAccount paymentAccount = paymentAccountDAO.findExistingPaymentAccount(accountNumber);
        // if (existingSavingAccount != null) {
        // if (existingSavingAccount.getAccountNumber().equals(accountNumber)) {
        // throw new HandleException("The Saving Account " + accountNumber
        // + " is already existed.", 409);
        // }
        // } else {

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
        // }
        return null;
    }

    public Map<String, Double> displayExpectedSaving(String accountNumber, String strDate) {
        SavingAccount savingAccount = findByAccountNumber(accountNumber);
        InterestRateDAO interestRateDAO = new InterestRateDAO();
        InterestRate interestRate = new InterestRate();

        Double amount = savingAccount.getSavingInitialAmount();
        LocalDate checkDate = LocalDate.parse(strDate);

        // For calculating interest
        int numberOfDay = (checkDate.getMonthValue() - savingAccount.getDateOpened().getMonthValue());
        interestRate = interestRateDAO.findInterestRateByInterestId(savingAccount.getInterestRate().getInterestId());
        Map<String, Double> map = new HashMap<String, Double>();
        
        if (savingAccount.getDateOpened().isBefore(checkDate)){
            map = calculateInterest(amount, interestRate.getConsecutive(), interestRate, savingAccount, checkDate);
            return map;
        }
        else if (savingAccount.getDateOpened().getMonthValue() == checkDate.getMonthValue()){
            map.put("expectedTotal", amount);
            map.put("monthlyTotal", 0.0);
            return map;
        }
        else{
            map.put("expectedTotal", 0.0);
            map.put("monthlyTotal", 0.0);
            return map;
        }
    }

    public Map<String, Double> calculateInterest(Double amount, boolean cons, InterestRate rate, SavingAccount savingAccount, LocalDate checkDate) {
        Map<String, Double> result = new HashMap<>();
        Double expectedTotal = 0.0;
        Double interest = ((rate.getInterestRate() * 1.0) / 100) / 12;
        int term = rate.getTerm();

        expectedTotal = amount * (Math.pow((1 + interest), term * 1.0));

        // Calculate the number of months (considering partial months)
        YearMonth startYearMonth = YearMonth.from(savingAccount.getDateOpened());
        YearMonth endYearMonth = YearMonth.from(checkDate);
        long monthsDifference = ChronoUnit.MONTHS.between(startYearMonth, endYearMonth);

        // Round up to the nearest whole month
        int consTime = (int) Math.ceil(monthsDifference);
        System.out.println("consTime " + consTime);
        Double monthlyTotal = 0.0;
        
        monthlyTotal = amount * (Math.pow((1 + interest), consTime * 1.0));

        // Format totalAmount to 2 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        expectedTotal = Double.parseDouble(decimalFormat.format(expectedTotal));
        monthlyTotal = Double.parseDouble(decimalFormat.format(monthlyTotal));

        result.put("expectedTotal", expectedTotal);
        result.put("monthlyTotal", monthlyTotal);

        return result;
    }

    public SavingAccount updateCurrentSavingAccount(String accountNumber, SavingAccount savingAccount, InterestRate rate){
        Double interest = ((rate.getInterestRate() * 1.0) / 100) / 12;

        // Calculate the number of months (considering partial months)
        YearMonth startYearMonth = YearMonth.from(savingAccount.getDateOpened());
        YearMonth endYearMonth = YearMonth.from(LocalDate.now());
        long monthsDifference = ChronoUnit.MONTHS.between(startYearMonth, endYearMonth);

        // Round up to the nearest whole month
        int consTime = (int) Math.ceil(monthsDifference);

        Double currentAmount = savingAccount.getSavingInitialAmount() * (Math.pow((1 + interest), consTime * 1.0));
        
        savingAccount.setSavingCurrentAmount(currentAmount);
        update(savingAccount);
        return savingAccount;
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
