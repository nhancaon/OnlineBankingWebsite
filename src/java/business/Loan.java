package business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "loan")
public class Loan implements Serializable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cus_id",referencedColumnName = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "loan",cascade = CascadeType.ALL)
    private List<LoanPayment> loanPayments;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanId;
    @Column(columnDefinition = "varchar(20)")
    private String loanType;
    private Double loanAmount;
    private Double interestRate;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(columnDefinition = "varchar(20)")
    private String loanStatus;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LoanPayment> getLoanPayments() {
        return loanPayments;
    }

    public void setLoanPayments(List<LoanPayment> loanPayments) {
        this.loanPayments = loanPayments;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

}
