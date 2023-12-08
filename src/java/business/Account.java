package business;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Account implements Serializable {

    private String accountNumber;
    private String accountType;
    private LocalDate dateOpened;
    private LocalDate dateClosed;
    private String accountStatus;


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDate getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDate dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
