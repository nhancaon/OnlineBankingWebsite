package business;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "savingAccount")
public class SavingAccount extends Account implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "payAc_id", referencedColumnName = "paymentAccountId")
    private PaymentAccount paymentAccount;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "interest_rate_id", referencedColumnName = "interestId")
    private InterestRate interestRate;

    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String savingAccountId;
    @Column(nullable = false)
    private int minBalance;
    @Column(nullable = false)
    private Double savingAmount;

    public String getSavingAccountId() {
        return savingAccountId;
    }

    public void setSavingAccountId(String savingAccountId) {
        this.savingAccountId = savingAccountId;
    }

    public int getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(int minBalance) {
        this.minBalance = minBalance;
    }

    public Double getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(Double savingAmount) {
        this.savingAmount = savingAmount;
    }

    public InterestRate getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }
}
