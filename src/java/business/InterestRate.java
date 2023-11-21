package business;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "interestRate")
public class InterestRate implements Serializable {

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "sa_id", referencedColumnName = "savingAccountId")
    private SavingAccount savingAccount;
    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String interestId;
    @Column(nullable = false)
    private float interestRate;
    @Column(columnDefinition = "varchar(20)", nullable = true)
    private String savingTitle;
    @Column(columnDefinition = "varchar(20)", nullable = true)
    private String loanTitle;

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

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
}