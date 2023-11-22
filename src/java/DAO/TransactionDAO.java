package DAO;

import business.PaymentAccount;
import business.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import Exception.HandleException;

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

    public void createTransaction(PaymentAccount sender, PaymentAccount receiver, String transactionRemark, Double amount, LocalDateTime time) throws HandleException {
        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        Transaction transactionEntity = new Transaction();
        transactionEntity.setTransactionId(generateUniqueId());
        transactionEntity.setSender(sender);
        transactionEntity.setReceiver(receiver);
        transactionEntity.setTransactionRemark(transactionRemark);
        transactionEntity.setAmount(amount);
        transactionEntity.setTransactionDate(time);
        sender.setCurrentBalence(sender.getCurrentBalence() - amount.intValue());
        sender.setRewardPoint((int) (sender.getRewardPoint() + amount/10000));
        receiver.setCurrentBalence(receiver.getCurrentBalence() + amount.intValue());
        create(transactionEntity);
        paymentAccountDAO.update(sender);
        paymentAccountDAO.update(receiver);
    }

    public void checkTransaction(PaymentAccount sender, PaymentAccount receiver, String transactionRemark, Double amount, LocalDateTime time) throws HandleException {
        if (sender == null) {
            throw new HandleException("Please add your payment account before transfer", 409);
        } else if (receiver == null) {
            throw new HandleException("This account isn't exist", 409);
        } else if (sender.getCurrentBalence() < amount) {
            throw new HandleException("Your account is not enough", 409);
        } else if (sender.getAccountNumber().equals(receiver.getAccountNumber())) {
            throw new HandleException("Can not transfer to yourself", 409);
        } else if (transactionRemark == null || amount == null || time == null || transactionRemark.isEmpty()) {
            throw new HandleException("Please fill out all information.", 409);
        }
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
    
    public List<Transaction> findTransactionOfPaymentAccountId(String paymentAccountId  ) {

        List<Transaction> transactionList = super.findWithNamedQuery(
                "SELECT tran FROM Transaction tran WHERE tran.sender.paymentAccountId = :paymentAccountId OR tran.receiver.paymentAccountId = :paymentAccountId ORDER BY tran.transactionDate DESC",
                "paymentAccountId",
                paymentAccountId
        );
        if (!transactionList.isEmpty()) {
            return transactionList;
        }

        return null;
    }
}
