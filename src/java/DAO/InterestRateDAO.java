package DAO;

import Exception.HandleException;
import business.InterestRate;
import business.LoanLending;

import java.util.List;

public class InterestRateDAO extends JpaDAO<InterestRate> implements GenericDAO<InterestRate> {

    @Override
    public InterestRate create(InterestRate t) {
        return super.create(t);
    }

    @Override
    public InterestRate get(Object id) {
        return super.find(InterestRate.class, id);
    }

    @Override
    public InterestRate update(InterestRate t) {
        return super.update(t);
    }

    @Override
    public void delete(Object id) {
        super.delete(InterestRate.class, id);

    }

    @Override
    public List<InterestRate> listAll() {
        return super.findWithNamedQuery("SELECT i FROM InterestRate i WHERE 1 = :i",
                "i",
                1);
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("");
    }

    public List<InterestRate> findAllInterestRate() {

        List<InterestRate> result = super.findWithNamedQuery("SELECT i FROM InterestRate i");

        if (!result.isEmpty()) {
            return result;
        }

        return null;
    }

    public InterestRate findInterestRateByCusId(String customerId) {

        List<InterestRate> savingAccountList = super.findWithNamedQuery(
                "SELECT sa FROM InterestRate sa WHERE sa.customer.customerId = :customerId",
                "customerId",
                customerId);
        if (!savingAccountList.isEmpty()) {
            return savingAccountList.get(0);
        }

        return null;
    }

    public InterestRate findBySavingTitle(String savingTitle) {

        List<InterestRate> result = super.findWithNamedQuery("SELECT i FROM InterestRate i WHERE i.savingTitle = :savingTitle", "savingTitle", savingTitle);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public InterestRate findByLoanTitle(String loanTitle) {

        List<InterestRate> result = super.findWithNamedQuery("SELECT i FROM InterestRate i WHERE i.loanTitle = :loanTitle", "loanTitle",loanTitle);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }
}
