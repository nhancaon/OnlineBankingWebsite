package business;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "sender_id", referencedColumnName = "paymentAccountId",nullable = false)
    private PaymentAccount sender;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "receiver_id", referencedColumnName = "paymentAccountId",nullable = false)
    private PaymentAccount receiver;

    @Id
    @Column(columnDefinition = "varchar(20)",nullable = false)
    private String transactionId;
    @Column(columnDefinition = "varchar(100)",nullable = false)
    private String transactionRemark;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    public Transaction() {

    }

    public PaymentAccount getSender() {
        return sender;
    }

    public void setSender(PaymentAccount sender) {
        this.sender = sender;
    }

    public PaymentAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(PaymentAccount receiver) {
        this.receiver = receiver;
    }
    
    //Constructor for other fields.....

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionRemark() {
        return transactionRemark;
    }

    public void setTransactionRemark(String transactionRemark) {
        this.transactionRemark = transactionRemark;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
