package business;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "savingAccount")
@AttributeOverride(name = "accountNumber", column = @Column(columnDefinition = "varchar(20)", nullable = false))
@AttributeOverride(name = "accountType", column = @Column(columnDefinition = "varchar(20)", nullable = false))
@AttributeOverride(name = "dateOpened", column = @Column(nullable = false))
@AttributeOverride(name = "dateClosed", column = @Column(nullable = false))
@AttributeOverride(name = "accountStatus", column = @Column(columnDefinition = "varchar(20)", nullable = false))
@AttributeOverride(name = "pinNumber", column = @Column(nullable = false))
public class SavingAccount extends Account implements Serializable {

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cus_id",referencedColumnName = "customerId")
    private Customer customer;

    @Id
    @Column(columnDefinition = "varchar(20)",nullable = false)
    private String savingAccountId;
    @Column(nullable = false)
    private int minBalence;
    @Column(nullable = false)
    private int savingAmount;
    @Column(nullable = false)
    private int interestRate;

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

    public int getMinBalence() {
        return minBalence;
    }

    public void setMinBalence(int minBalence) {
        this.minBalence = minBalence;
    }

    public int getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(int savingAmount) {
        this.savingAmount = savingAmount;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

}
