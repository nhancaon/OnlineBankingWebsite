package DAO;

import Exception.HandleException;
import business.Customer;
import business.InterestRate;
import business.SavingAccount;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<SavingAccount> findSavingAccountByCusId(String customerId) {

        List<SavingAccount> savingAccountList = super.findWithNamedQuery(
                "SELECT sa FROM SavingAccount sa WHERE sa.customer.customerId = :customerId",
                "customerId",
                customerId
        );
        if (!savingAccountList.isEmpty()) {
            return savingAccountList;
        }

        return null;
    }

    public SavingAccount findExistingSavingAccount(String customerId, String accountNumber) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", customerId);
        parameters.put("accountNumber", accountNumber);

        List<SavingAccount> savingAccountList = super.findWithNamedQuery(
                "SELECT sa FROM SavingAccount sa WHERE sa.customer.customerId = :customerId AND sa.accountNumber = :accountNumber",
                parameters
        );

        if (!savingAccountList.isEmpty()) {
            return savingAccountList.get(0);
        }

        return null;
    }

    public SavingAccount CreateSavingAccount(Customer customer, String accountNumber, String accountType, int term, int amount, InterestRate interestRate) throws HandleException {

        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        SavingAccount savingAccountEntity = new SavingAccount();
        SavingAccount existingSavingAccount = findExistingSavingAccount(customer.getCustomerId(), accountNumber);
        PaymentAccount savingAccount = paymentAccountDAO.findExistingPaymentAccount(accountNumber);
        if (existingSavingAccount != null) {
            if (existingSavingAccount.getAccountNumber().equals(accountNumber)) {
                throw new HandleException("The Saving Account " + accountNumber
                        + " is already existed.", 409);
            }
        } else {

            if (amount < 1000000) {
                throw new HandleException("The Saving Amount need to be more than 1000000 VND", 409);
            } else if (accountNumber == null || accountNumber.isEmpty() || accountType == null || accountType.isEmpty()) {
                throw new HandleException("Please fill in the form", 409);
            } else if (interestRate == null) {
                throw new HandleException("Something went wrong", 409);
            } else if (amount > savingAccount.getCurrentBalence()) {
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
                savingAccountEntity.setSavingAmount(amount);
                savingAccountEntity.setCustomer(customer);
                savingAccountEntity.setInterestRate(interestRate);
                savingAccount.setCurrentBalence(savingAccount.getCurrentBalence() - amount);
                paymentAccountDAO.update(savingAccount);
                create(savingAccountEntity);
            }
        }
        return null;
    }

    public SavingAccount findByAccountNumber(String accountNumber) {

        List<SavingAccount> result = super.findWithNamedQuery("SELECT sa FROM SavingAccount sa WHERE sa.accountNumber = :accountNumber", "accountNumber", accountNumber);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public void Withdraw(SavingAccount savingAccount, int expectedAmount) throws HandleException {

        SavingAccount savingAccountEntity = savingAccount;
        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        SavingAccountDAO savingAccountDAO = new SavingAccountDAO();
        PaymentAccount defaultAc = paymentAccountDAO.findDefaultPaymentAccount(savingAccount.getCustomer().getCustomerId());
        if (savingAccount.getDateClosed().compareTo(LocalDate.now()) > 0) {
            throw new HandleException("You have not yet reached the withdrawal date.", 409);
        } else if (defaultAc == null) {
            throw new HandleException("Please add your default payment account.", 409);
        } else {
            savingAccountEntity.setAccountStatus("Inactive");
            defaultAc.setCurrentBalence(defaultAc.getCurrentBalence() + expectedAmount);
            paymentAccountDAO.update(defaultAc);
            savingAccountDAO.update(savingAccountEntity);
        }
    }
}
