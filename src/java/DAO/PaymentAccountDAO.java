package DAO;

import business.Customer;
import business.PaymentAccount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

    public List<PaymentAccount> findPaymentAccountByCusId(String customerId) {

        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.customer.customerId = :customerId",
                "customerId",
                customerId
        );
        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList;
        }

        return null;
    }

    public PaymentAccount findExistingPaymentAccount(String customerId, String accountNumber) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", customerId);
        parameters.put("accountNumber", accountNumber);

        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.customer.customerId = :customerId",
                "customerId",
                customerId
        );

        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList.get(0);
        }

        return null;
    }

    public PaymentAccount CreatePaymentAccount(Customer customer, String accountNumber, String pinNumber) {

        PaymentAccount paymentAccountEntity = new PaymentAccount();

        paymentAccountEntity.setPaymentAccountId(generateUniqueId());
        paymentAccountEntity.setCustomer(customer);
        paymentAccountEntity.setAccountNumber(accountNumber);
        paymentAccountEntity.setPinNumber(Integer.parseInt(pinNumber));
        paymentAccountEntity.setAccountStatus("Active");
        paymentAccountEntity.setAccountType("Classic");

        create(paymentAccountEntity);

        return null;
    }

    public PaymentAccount findByAccountNumber(String accountNumber) {

        List<PaymentAccount> result = super.findWithNamedQuery("SELECT pa FROM PaymentAccount pa WHERE pa.accountNumber = :accountNumber", "accountNumber", accountNumber);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }
}
