package DAO;

import business.InterestRate;
import business.PaymentAccount;
import business.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import Exception.HandleException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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

    public List<Transaction> findAllTransaction() {

        List<Transaction> result = super.findWithNamedQuery("SELECT i FROM Transaction i");

        if (!result.isEmpty()) {
            return result;
        }

        return null;
    }

    public void createTransaction(PaymentAccount sender, PaymentAccount receiver, String transactionRemark, Double amount,
            LocalDateTime time, String OTP, String enteredOTP) throws HandleException {
        if (!OTP.equals(enteredOTP)) {
            throw new HandleException("Invalid OTP", 409);
        } else {
            PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
            Transaction transactionEntity = new Transaction();
            transactionEntity.setTransactionId(generateUniqueId());
            transactionEntity.setSender(sender);
            transactionEntity.setReceiver(receiver);
            transactionEntity.setTransactionRemark(transactionRemark);
            transactionEntity.setAmount(amount);
            transactionEntity.setTransactionDate(time);
            sender.setCurrentBalence(sender.getCurrentBalence() - amount);
            sender.setRewardPoint((int) (sender.getRewardPoint() + amount / 10000));
            receiver.setCurrentBalence(receiver.getCurrentBalence() + amount);
            create(transactionEntity);
            paymentAccountDAO.update(sender);
            paymentAccountDAO.update(receiver);
        }

    }

    public void checkTransaction(PaymentAccount sender, PaymentAccount receiver, String transactionRemark,
            Double amount, LocalDateTime time) throws HandleException {
        if (sender == null) {
            throw new HandleException("Please add your payment account before transfer", 409);
        } else if (receiver == null) {
            throw new HandleException("This account isn't exist", 409);
        } else if (receiver == sender) {
            throw new HandleException("This is your current account", 409);
        } else if (sender.getCurrentBalence() < amount) {
            throw new HandleException("Your account is not enough", 409);
        } else if (sender.getAccountNumber().equals(receiver.getAccountNumber())) {
            throw new HandleException("Can not transfer to yourself", 409);
        } else if (transactionRemark == null || amount == null || time == null || transactionRemark.isEmpty()) {
            throw new HandleException("Please fill out all information.", 409);
        }else if(sender.getAccountType().equals("Classic")){
            if(checkQuota(sender.getPaymentAccountId()) + amount > 5.0E7){
                throw new HandleException("You have reached your maximum limit for today", 409);
            }
        }else if(!sender.getAccountType().equals("Classic")){
            if(checkQuota(sender.getPaymentAccountId()) + amount > 1.0E8){
                throw new HandleException("You have reached your maximum limit for today", 409);
            }
        }
    }

    public List<Transaction> findTransactionsByCusId(String paymentAccountId) {

        List<Transaction> transactionList = super.findWithNamedQuery(
                "SELECT tra FROM Transaction tra WHERE tra.sender.paymentAccountId = :paymentAccountId",
                "paymentAccountId",
                paymentAccountId);
        if (!transactionList.isEmpty()) {
            return transactionList;
        }

        return null;
    }

    public List<Transaction> findTransactionOfPaymentAccountId(String paymentAccountId) {

        List<Transaction> transactionList = super.findWithNamedQuery(
                "SELECT tran FROM Transaction tran WHERE tran.sender.paymentAccountId = :paymentAccountId OR tran.receiver.paymentAccountId = :paymentAccountId ORDER BY tran.transactionDate DESC",
                "paymentAccountId",
                paymentAccountId);
        if (!transactionList.isEmpty()) {
            return transactionList;
        }

        return null;
    }
    
    public Double checkQuota(String paymentAccountId) {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        EntityManager entityManager = JpaDAO.getEmFactory().createEntityManager();
        // Query to sum the amount sent by the sender today
        Query query = entityManager.createQuery("SELECT SUM(t.amount) FROM Transaction t " +
                "WHERE t.sender.paymentAccountId = :accountId " +
                "AND t.transactionDate BETWEEN :startOfDay AND :endOfDay");
        query.setParameter("accountId", paymentAccountId);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);
        Double totalSentAmountToday = (Double) query.getSingleResult();
        if (totalSentAmountToday == null) {
            totalSentAmountToday = 0.0;
        }
        return totalSentAmountToday;
    }
}
