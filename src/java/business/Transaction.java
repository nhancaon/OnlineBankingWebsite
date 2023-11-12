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
    @JoinColumn(name = "sender_id", referencedColumnName = "paymentAccountId")
    private PaymentAccount sender;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "receiver_id", referencedColumnName = "paymentAccountId")
    private PaymentAccount receiver;

    @Id
    @Column(columnDefinition = "varchar(20)")
    private String transactionId;
    @Column(columnDefinition = "varchar(20)")
    private String transactionRemake;
    private Double amount;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionRemake() {
        return transactionRemake;
    }

    public void setTransactionRemake(String transactionRemake) {
        this.transactionRemake = transactionRemake;
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
