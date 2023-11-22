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
    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String interestId;
    @Column(nullable = false)
    private float interestRate;
    @Column(columnDefinition = "varchar(20)", nullable = true)
    private String savingTitle;
    @Column(columnDefinition = "varchar(20)", nullable = true)
    private String loanTitle;
    private int term;

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
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

}
