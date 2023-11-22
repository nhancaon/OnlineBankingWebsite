package business;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "savingAccount")
public class SavingAccount extends Account implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cus_id", referencedColumnName = "customerId")
    private Customer customer;

    @OneToOne(mappedBy = "savingAccount", cascade = CascadeType.ALL)
    private InterestRate interestRate;

    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String savingAccountId;
    @Column(nullable = false)
    private int minBalance;
    @Column(nullable = false)
    private int savingAmount;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public int getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(int savingAmount) {
        this.savingAmount = savingAmount;
    }
}
