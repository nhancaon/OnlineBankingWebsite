package business;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loanLending")
public class LoanLending extends Account implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cus_id", referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "interest_rate_id", referencedColumnName = "interestId")
    private InterestRate interestRate;

    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String loanLendingId;
    @Column(nullable = false)
    private int loanAmount;
    @Column(nullable = false)
    private Double totalLoanAmount;
    @Column(nullable = false)
    private Double monthlyPay;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLoanLendingId() {
        return loanLendingId;
    }

    public void setLoanLendingId(String loanLendingId) {
        this.loanLendingId = loanLendingId;
    }

    public Double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(Double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(Double monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public InterestRate getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }
}

