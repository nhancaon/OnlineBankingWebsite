package DAO;

import business.PaymentAccount;
import business.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class TransactionDAO extends JpaDAO<Transaction> implements GenericDAO<Transaction> {

    @Override
    public Transaction create(Transaction t) {
        return super.create(t);
    }

    @Override
    public Transaction get(Object id) {
        return super.find(Transaction.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Transaction.class, id);

    }

    @Override
    public List<Transaction> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

    public Transaction getTransactionById(int transactionId) {
        return find(Transaction.class, transactionId);
    }

    // Additional method for select operation
//    public List<Transaction> getAllTransactions() {
//        EntityManager em = JpaDAO.getEmFactory().createEntityManager();
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
    public void createTransaction(String senderNumber, String receiverNumber, String transactionRemark, Double amount) {
        EntityManager em = JpaDAO.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        
        try {
            trans.begin();
            PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
            PaymentAccount sender = paymentAccountDAO.findByAccountNumber(senderNumber);
            PaymentAccount receiver = paymentAccountDAO.findByAccountNumber(receiverNumber);

            Transaction transactionEntity = new Transaction();
            transactionEntity.setTransactionId(generateUniqueId());
            transactionEntity.setSender(sender);
            transactionEntity.setReceiver(receiver);
            transactionEntity.setTransactionRemake(transactionRemark);
            transactionEntity.setAmount(amount);
            transactionEntity.setTransactionDate(LocalDateTime.now());

            sender.setCurrentBalence(sender.getCurrentBalence() - amount.intValue());
            receiver.setCurrentBalence(receiver.getCurrentBalence() + amount.intValue());

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
