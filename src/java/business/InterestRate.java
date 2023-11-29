package business;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "interestRate")
public class InterestRate implements Serializable {

    @OneToMany(mappedBy = "interestRate", cascade = CascadeType.DETACH)
    private List<SavingAccount> savingAccounts;

    @OneToMany(mappedBy = "interestRate", cascade = CascadeType.DETACH)
    private List<LoanLending> loanLendings;
    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String interestId;
    @Column(nullable = false)
    private Double interestRate;
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String savingTitle;
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String loanTitle;
    @Column(columnDefinition = "int", nullable = false)
    private int term;
    @Column(columnDefinition = "boolean", nullable = false)
    private Boolean consecutive;

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getSavingTitle() {
        return savingTitle;
    }

    public void setSavingTitle(String savingTitle) {
        this.savingTitle = savingTitle;
    }

    public String getLoanTitle() {
        return loanTitle;
    }

    public void setLoanTitle(String loanTitle) {
        this.loanTitle = loanTitle;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public List<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(List<SavingAccount> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    public List<LoanLending> getLoanLendings() {
        return loanLendings;
    }

    public void setLoanLendings(List<LoanLending> loanLendings) {
        this.loanLendings = loanLendings;
    }

    public Boolean getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(Boolean consecutive) {
        this.consecutive = consecutive;
    }
}
