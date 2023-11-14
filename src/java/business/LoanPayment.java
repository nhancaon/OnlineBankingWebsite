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
@Table(name = "loanPayment")
public class LoanPayment implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "loan_id",referencedColumnName = "loanId",nullable = false)
    private Loan loan;

    @Id
    @Column(columnDefinition = "varchar(20)",nullable = false)
    private String loanPaymentId;
    @Column(nullable = false)
    private LocalDate scheduledPaymentDate;
    @Column(nullable = false)
    private Double paymentAmount;
    @Column(nullable = false)
    private Double principalAmount;
    @Column(nullable = false)
    private Double interestAmount;
    @Column(nullable = false)
    private Double paidAmount;
    @Column(nullable = false)
    private LocalDate paidDate;

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public String getLoanPaymentId() {
        return loanPaymentId;
    }

    public void setLoanPaymentId(String loanPaymentId) {
        this.loanPaymentId = loanPaymentId;
    }

    public LocalDate getScheduledPaymentDate() {
        return scheduledPaymentDate;
    }

    public void setScheduledPaymentDate(LocalDate scheduledPaymentDate) {
        this.scheduledPaymentDate = scheduledPaymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(Double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }
}
