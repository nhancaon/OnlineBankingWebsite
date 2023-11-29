package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "paymentAccount")
public class PaymentAccount extends Account implements Serializable {

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "reward_of_account",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "paymentAccountId"),
            inverseJoinColumns = @JoinColumn(name = "reward_id", referencedColumnName = "rewardId")
    )
    private List<Reward> rewards;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cus_id", referencedColumnName = "customerId", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.DETACH)
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.DETACH)
    private List<Transaction> receivedTransactions;

    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String paymentAccountId;
    @Column(nullable = false)
    private Double currentBalence;
    @Column(nullable = false)
    private int rewardPoint;

    public PaymentAccount() {
        this.setDateOpened(LocalDate.now());

        // Set dateClosed to 1 year after dateOpened
        this.setDateClosed(LocalDate.now().plus(1, ChronoUnit.YEARS));
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(String paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    public Double getCurrentBalence() {
        return currentBalence;
    }

    public void setCurrentBalence(Double currentBalence) {
        this.currentBalence = currentBalence;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

}
