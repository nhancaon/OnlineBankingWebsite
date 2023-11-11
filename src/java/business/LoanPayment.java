package business;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "loanPayment")
public class LoanPayment implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "loan_id",referencedColumnName = "loanId")
    private Loan loan;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanPaymentId;
    private LocalDate scheduledPaymentDate;
    private Double paymentAmount;
    private Double principalAmount;
    private Double interestAmount;
    private Double paidAmount;
    private LocalDate paidDate;

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public int getLoanPaymentId() {
        return loanPaymentId;
    }

    public void setLoanPaymentId(int loanPaymentId) {
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
