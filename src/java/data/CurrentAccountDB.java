package data;

import business.CurrentAccount;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CurrentAccountDB {

    public static void insert(CurrentAccount transac) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(transac);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void update(CurrentAccount transac) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(transac);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(CurrentAccount transac) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(transac));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

//    public static CurrentAccount getCurrentAccountById(String acNumber) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        return em.find(CurrentAccount.class, acNumber);
//    }
     public static CurrentAccount createCurrentAccount(String accountNumber) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT ca FROM CurrentAccount ca WHERE ca.accountNumber = :accountNumber";
        TypedQuery<CurrentAccount> q = em.createQuery(qString, CurrentAccount.class);
        q.setParameter("accountNumber", accountNumber);
        CurrentAccount ca = null;
        try {
            ca = q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
        return (CurrentAccount) ca;
    }

     
    public static CurrentAccount findByAccountNumber(String accountNumber) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT ca FROM CurrentAccount ca WHERE ca.accountNumber = :accountNumber";
        TypedQuery<CurrentAccount> q = em.createQuery(qString, CurrentAccount.class);
        q.setParameter("accountNumber", accountNumber);
        CurrentAccount ca = null;
        try {
            ca = q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
        return (CurrentAccount) ca;
    }

    // Additional method for select operation
    public List<CurrentAccount> getAllCurrentAccounts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT t FROM CurrentAccount t";
        TypedQuery<CurrentAccount> q = em.createQuery(qString, CurrentAccount.class);
        List<CurrentAccount> transactions = null;
        try {
            transactions = q.getResultList();
        } catch (NoResultException e) {
            // Handle appropriately, for example, logging or rethrowing
        } finally {
            em.close();
        }
        return transactions;
    }
}
