package business;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User implements Serializable {

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private SavingAccount savingAccount;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<PaymentAccount> paymentAccounts;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Loan> loans;

    @Id
    @Column(columnDefinition = "varchar(20)")
    private String customerId;
    @Column(columnDefinition = "varchar(20)")
    private String customerType;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public List<PaymentAccount> getCurrentAccounts() {
        return paymentAccounts;
    }

    public void setCurrentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

}
