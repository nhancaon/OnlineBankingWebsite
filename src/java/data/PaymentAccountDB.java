package data;

import business.PaymentAccount;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class PaymentAccountDB {

//    public static void insert(PaymentAccount transac) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.persist(transac);
//            trans.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//    }
//
//    public static void update(PaymentAccount transac) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.merge(transac);
//            trans.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//    }
//
//    public static void delete(PaymentAccount transac) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.remove(em.merge(transac));
//            trans.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//    }

//    public static CurrentAccount getCurrentAccountById(String acNumber) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        return em.find(CurrentAccount.class, acNumber);
//    }
    public static PaymentAccount findByAccountNumber(String accountNumber) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
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
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
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
