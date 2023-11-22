package DAO;

import Exception.HandleException;
import business.Customer;
import business.SavingAccount;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public SavingAccount findSavingAccountByCusId(String customerId) {

        List<SavingAccount> savingAccountList = super.findWithNamedQuery(
                "SELECT sa FROM SavingAccount sa WHERE sa.customer.customerId = :customerId",
                "customerId",
                customerId
        );
        if (!savingAccountList.isEmpty()) {
            return savingAccountList.get(0);
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

    public SavingAccount CreateSavingAccount(Customer customer, String accountNumber, String pinNumber,int amount) throws HandleException {

        SavingAccount savingAccountEntity = new SavingAccount();
        SavingAccount existingSavingAccount = findExistingSavingAccount(customer.getCustomerId(), accountNumber);
        if (existingSavingAccount != null) {

            System.out.print(existingSavingAccount.getAccountNumber());
            if (existingSavingAccount.getAccountNumber().equals(accountNumber)) {
                throw new HandleException("The Saving Account " + accountNumber
                        + " is already existed.", 409);
            }
        } else {
            LocalDate time = LocalDate.now();
            savingAccountEntity.setSavingAccountId(generateUniqueId());
            savingAccountEntity.setAccountNumber(accountNumber);
            savingAccountEntity.setAccountStatus("Active");
            savingAccountEntity.setAccountType("Year");
            savingAccountEntity.setDateOpened(time);
            savingAccountEntity.setDateClosed(time.plusYears(1));
            savingAccountEntity.setMinBalance(1000000);
            savingAccountEntity.setPinNumber(Integer.parseInt(pinNumber));
            savingAccountEntity.setSavingAmount(amount);
            savingAccountEntity.setCustomer(customer);
            create(savingAccountEntity);

        }
        return null;
    }

//    public SavingAccount findByAccountNumber(String accountNumber) {
//
//        List<SavingAccount> result = super.findWithNamedQuery("SELECT sa FROM SavingAccount sa WHERE sa.accountNumber = :accountNumber", "accountNumber", accountNumber);
//
//        if (!result.isEmpty()) {
//            return result.get(0);
//        }
//
//        return null;
//    }
}
