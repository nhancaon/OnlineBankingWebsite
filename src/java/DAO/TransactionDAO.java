package DAO;

import business.PaymentAccount;
import business.Transaction;
import java.time.LocalDateTime;
import java.util.List;

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

    public void createTransaction(PaymentAccount sender, PaymentAccount receiver, String transactionRemark, Double amount, LocalDateTime time) {
        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

        Transaction transactionEntity = new Transaction();
        transactionEntity.setTransactionId(generateUniqueId());
        transactionEntity.setSender(sender);
        transactionEntity.setReceiver(receiver);
        transactionEntity.setTransactionRemake(transactionRemark);
        transactionEntity.setAmount(amount);
        transactionEntity.setTransactionDate(time);
        sender.setCurrentBalence(sender.getCurrentBalence() - amount.intValue());
        receiver.setCurrentBalence(receiver.getCurrentBalence() + amount.intValue());
        create(transactionEntity);
        paymentAccountDAO.update(sender);
        paymentAccountDAO.update(receiver);
    }
    
    public List<Transaction> findTransactionsByCusId(String paymentAccountId) {

        List<Transaction> transactionList = super.findWithNamedQuery(
                "SELECT tra FROM Transaction tra WHERE tra.sender.paymentAccountId = :paymentAccountId",
                "paymentAccountId",
                paymentAccountId
        );
        if (!transactionList.isEmpty()) {
            return transactionList;
        }

        return null;
    }
}
