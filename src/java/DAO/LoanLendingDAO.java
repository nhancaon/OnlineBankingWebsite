package DAO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exception.HandleException;
import business.Customer;
import business.InterestRate;
import business.LoanLending;
import DAO.PaymentAccountDAO;
import business.PaymentAccount;

public class LoanLendingDAO extends JpaDAO<LoanLending> implements GenericDAO<LoanLending> {

    @Override
    public LoanLending create(LoanLending t) {
        return super.create(t);
    }

    @Override
    public LoanLending get(Object id) {
        return super.find(LoanLending.class, id);
    }

    @Override
    public LoanLending update(LoanLending t) {
        return super.update(t);
    }

    @Override
    public void delete(Object id) {
        super.delete(LoanLending.class, id);

    }

    @Override
    public List<LoanLending> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("");
    }

    public List<LoanLending> findLoanLendingByCusId(String customerId) {
        List<LoanLending> loanLendingList = super.findWithNamedQuery(
                "SELECT ll FROM LoanLending ll WHERE ll.customer.customerId = :customerId",
                "customerId",
                customerId
        );
        if (!loanLendingList.isEmpty()) {
            return loanLendingList;
        }

        return null;
    }

    public LoanLending findExistingLoanLending(String customerId, String accountNumber) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", customerId);
        parameters.put("accountNumber", accountNumber);

        List<LoanLending> loanLendingList = super.findWithNamedQuery(
                "SELECT ll FROM LoanLending ll WHERE ll.customer.customerId = :customerId AND ll.accountNumber = :accountNumber",
                parameters
        );

        if (!loanLendingList.isEmpty()) {
            return loanLendingList.get(0);
        }

        return null;
    }

    public LoanLending CreateLoanLending(Customer customer, String accountNumber, String accountType, int term, int amount, InterestRate interestRate) throws HandleException {

        LoanLending loanLendingEntity = new LoanLending();
        LoanLending existingLoanLending = findExistingLoanLending(customer.getCustomerId(), accountNumber);
        if (existingLoanLending != null) {
            if (existingLoanLending.getAccountNumber().equals(accountNumber)) {
                throw new HandleException("The Loan Lending Account " + accountNumber + " is already existed.", 409);
            } else if (amount < 1000000) {
                throw new HandleException("The Loan Lending need to be more than 1000000 VND", 409);
            } else if (accountNumber == null || accountNumber.isEmpty() || accountType == null || accountType.isEmpty()) {
                throw new HandleException("Please fill in the form", 409);
            } else if (interestRate == null) {
                throw new HandleException("Something went wrong", 409);
            }
        } else {
            PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
            PaymentAccount defaultAc = paymentAccountDAO.findDefaultPaymentAccount(customer.getCustomerId());
            LocalDate time = LocalDate.now();

            loanLendingEntity.setLoanLendingId(generateUniqueId());
            loanLendingEntity.setAccountNumber(accountNumber);
            loanLendingEntity.setAccountStatus("In progress");
            loanLendingEntity.setAccountType(accountType);
            loanLendingEntity.setDateOpened(time);
            loanLendingEntity.setDateClosed(time.plusMonths(term));            
            loanLendingEntity.setLoanAmount(amount);

            //Total loan must pay
            Double monthYear = (term * 1.0 / 12.0);
            Double interest = monthYear * (interestRate.getInterestRate() / 100.0);
            Double totalPay = amount * 1.0 + amount * interest * monthYear;
            loanLendingEntity.setTotalLoanAmount(totalPay);

            // Monthly pay
                // Using DecimalFormat
            DecimalFormat df = new DecimalFormat("#.##");
            String formattedNumber = df.format(totalPay / (term*1.0));
            Double roundedNumber = Double.parseDouble(formattedNumber);
            loanLendingEntity.setMonthlyPay(roundedNumber);

            loanLendingEntity.setCustomer(customer);
            loanLendingEntity.setInterestRate(interestRate);

            defaultAc.setCurrentBalence(defaultAc.getCurrentBalence() + amount);
            paymentAccountDAO.update(defaultAc);
            create(loanLendingEntity);
        }
        return null;
    }

    public LoanLending findByAccountNumber(String accountNumber) {

        List<LoanLending> result = super.findWithNamedQuery("SELECT ll FROM LoanLending ll WHERE ll.accountNumber = :accountNumber", "accountNumber", accountNumber);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }
}
