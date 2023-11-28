package DAO;

import Exception.HandleException;
import business.Customer;
import business.PaymentAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountDAO extends JpaDAO<PaymentAccount> implements GenericDAO<PaymentAccount> {

    @Override
    public PaymentAccount create(PaymentAccount t) {
        return super.create(t);
    }

    @Override
    public PaymentAccount get(Object id) {
        return super.find(PaymentAccount.class, id);
    }

    @Override
    public PaymentAccount update(PaymentAccount t) {
        return super.update(t);
    }

    @Override
    public void delete(Object id) {
        super.delete(PaymentAccount.class, id);

    }

    @Override
    public List<PaymentAccount> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

    public List<PaymentAccount> findAllPaymentAccount() {

        List<PaymentAccount> result = super.findWithNamedQuery("SELECT pa FROM PaymentAccount pa");

        if (!result.isEmpty()) {
            return result;
        }
        return null;

    }

    public PaymentAccount setDefaultPaymentAccount(String customerId, String accountNumber) {

        PaymentAccount previousDefaultPaymentAccount = findDefaultPaymentAccount(customerId);
        previousDefaultPaymentAccount.setAccountStatus("Active");
        update(previousDefaultPaymentAccount);

        PaymentAccount currentPaymentAccount = findExistingPaymentAccount(accountNumber);
        currentPaymentAccount.setAccountStatus("Default");
        update(currentPaymentAccount);

        return null;
    }

    public PaymentAccount findDefaultPaymentAccount(String customerId) {

        String accountStatus = "Default";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", customerId);
        parameters.put("accountStatus", accountStatus);
        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.customer.customerId = :customerId AND pa.accountStatus = :accountStatus",
                parameters);

        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList.get(0);
        }

        return null;
    }

    public List<PaymentAccount> findPaymentAccountByCusId(String customerId) {

        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.customer.customerId = :customerId ORDER BY pa.accountStatus DESC",
                "customerId",
                customerId);
        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList;
        }

        return null;
    }

    public PaymentAccount findExistingPaymentAccount(String accountNumber) {

        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.accountNumber = :accountNumber",
                "accountNumber", accountNumber);

        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList.get(0);
        }

        return null;
    }

    public PaymentAccount CreatePaymentAccount(Customer customer, String accountNumber) throws HandleException {

        PaymentAccount paymentAccountEntity = new PaymentAccount();
        PaymentAccount existingPaymentAccount = findExistingPaymentAccount(accountNumber);
        PaymentAccount existingDefaultPaymentAccount = findDefaultPaymentAccount(customer.getCustomerId());
        if (existingPaymentAccount != null) {
            if (existingPaymentAccount.getAccountNumber().equals(accountNumber)) {
                throw new HandleException("The Payment Account " + accountNumber
                        + " is already existed.", 409);
            }
        } else {

            if (existingDefaultPaymentAccount != null) {
                paymentAccountEntity.setAccountStatus("Active");
            } else {
                paymentAccountEntity.setAccountStatus("Default");
            }
            paymentAccountEntity.setPaymentAccountId(generateUniqueId());
            paymentAccountEntity.setCustomer(customer);
            paymentAccountEntity.setAccountNumber(accountNumber);
            paymentAccountEntity.setAccountType("Classic");

            create(paymentAccountEntity);

        }
        return null;
    }

}
