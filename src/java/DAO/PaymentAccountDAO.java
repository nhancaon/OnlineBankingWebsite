package DAO;

import business.PaymentAccount;
import java.util.List;
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

    public static PaymentAccount findByAccountNumber(String accountNumber) {
        EntityManager em = JpaDAO.getEmFactory().createEntityManager();
        String qString = "SELECT pa FROM PaymentAccount pa WHERE pa.accountNumber = :accountNumber";
        TypedQuery<PaymentAccount> q = em.createQuery(qString, PaymentAccount.class);
        q.setParameter("accountNumber", accountNumber);
        PaymentAccount pa = null;
        try {
            pa = q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
        return (PaymentAccount) pa;
    }

    // Additional method for select operation
//    public List<PaymentAccount> getAllCurrentAccounts() {
//        EntityManager em = JpaDAO.getEmFactory().createEntityManager();
//        String qString = "SELECT t FROM CurrentAccount t";
//        TypedQuery<PaymentAccount> q = em.createQuery(qString, PaymentAccount.class);
//        List<PaymentAccount> transactions = null;
//        try {
//            transactions = q.getResultList();
//        } catch (NoResultException e) {
//            // Handle appropriately, for example, logging or rethrowing
//        } finally {
//            em.close();
//        }
//        return transactions;
//    }
}
