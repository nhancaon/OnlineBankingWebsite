package data;

import business.CurrentAccount;
import business.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class TransactionDB {

//    public static void insert(Transaction transac) {
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
//    public static void update(Transaction transac) {
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
//    public static void delete(Transaction transac) {
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

    public Transaction getTransactionById(int transactionId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        return em.find(Transaction.class, transactionId);
    }

    // Additional method for select operation
//    public List<Transaction> getAllTransactions() {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        String qString = "SELECT t FROM Transaction t";
//        TypedQuery<Transaction> q = em.createQuery(qString, Transaction.class);
//        List<Transaction> transactions = null;
//        try {
//            transactions = q.getResultList();
//        } catch (NoResultException e) {
//            // Handle appropriately, for example, logging or rethrowing
//        } finally {
//            em.close();
//        }
//        return transactions;
//    }

    public static void createTransaction(String senderNumber, String receiverNumber, String transactionRemark, Double amount) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();

            // Retrieve sender and receiver accounts
            CurrentAccount sender = CurrentAccountDB.findByAccountNumber(senderNumber);
            CurrentAccount receiver = CurrentAccountDB.findByAccountNumber(receiverNumber);

            // Create a new trans
            Transaction transactionEntity = new Transaction();
            transactionEntity.setSender(sender);
            transactionEntity.setReceiver(receiver);
            transactionEntity.setTransactionRemake(transactionRemark);
            transactionEntity.setAmount(amount);
            transactionEntity.setTransactionDate(LocalDateTime.now());

            // Update sender and receiver account balances (you may want to add proper balance update logic)
            sender.setCurrentBalence(sender.getCurrentBalence() - amount.intValue());
            receiver.setCurrentBalence(receiver.getCurrentBalence() + amount.intValue());

            // Persist the trans and update account balances
            em.persist(transactionEntity);
            em.merge(sender);
            em.merge(receiver);

            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        }
    }
}
