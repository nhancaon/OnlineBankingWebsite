package business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "sender_id",referencedColumnName = "currentAccountId")
    private CurrentAccount sender;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "receiver_id",referencedColumnName = "currentAccountId")
    private CurrentAccount receiver;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    @Column(columnDefinition = "varchar(20)")
    private String transactionType;
    private Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    public CurrentAccount getSender() {
        return sender;
    }

    public void setSender(CurrentAccount sender) {
        this.sender = sender;
    }

    public CurrentAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(CurrentAccount receiver) {
        this.receiver = receiver;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
