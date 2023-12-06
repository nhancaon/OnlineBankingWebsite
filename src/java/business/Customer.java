package business;

import java.io.Serializable;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@AttributeOverride(name = "name", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "dateofBirth", column = @Column(columnDefinition = "varchar(20)", nullable = false))
@AttributeOverride(name = "password", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "email", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "phoneNumber", column = @Column(columnDefinition = "varchar(20)", nullable = false))
@AttributeOverride(name = "address", column = @Column(columnDefinition = "varchar(100)", nullable = false))
@AttributeOverride(name = "citizenId", column = @Column(columnDefinition = "varchar(20)", nullable = false))
public class Customer extends User implements Serializable {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Beneficiary> beneficiarys;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<PaymentAccount> paymentAccounts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LoanLending> loanLendings;

    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String customerId;
    private int pinNumber;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    //Constructor for other fields.....

    public List<LoanLending> getLoans() {
        return loanLendings;
    }

    public void setLoans(List<LoanLending> loans) {
        this.loanLendings = loans;
    }

    public List<Beneficiary> getBeneficiarys() {
        return beneficiarys;
    }

    public void setBeneficiarys(List<Beneficiary> beneficiarys) {
        this.beneficiarys = beneficiarys;
    }

    public List<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public void setPaymentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
}
