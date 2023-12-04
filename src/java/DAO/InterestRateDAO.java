package DAO;

import Exception.HandleException;
import business.Customer;
import business.InterestRate;
import business.LoanLending;
import common.HashGenerator;
import common.MailSender;
import controller.User.SignupServlet;
import jakarta.mail.MessagingException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public InterestRate findInterestRateByInterestId(String interestId) {

        List<InterestRate> savingAccountList = super.findWithNamedQuery(
                "SELECT sa FROM InterestRate sa WHERE sa.interestId = :interestId",
                "interestId",
                interestId);
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

    public InterestRate findDuplicated(String loanTitle, String savingTitle){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("loanTitle", loanTitle);
        parameters.put("savingTitle", savingTitle);
        
        List<InterestRate> result = super.findWithNamedQuery(
            "SELECT ii FROM InterestRate ii WHERE ii.loanTitle = :loanTitle OR ii.savingTitle = :savingTitle", parameters);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public InterestRate addInterestRate(String interestRate, String loanTitle, String savingTitle, String term) throws HandleException {
        InterestRate interestRateEntity = new InterestRate();
        InterestRate existingInterestRate = findDuplicated(loanTitle, savingTitle);

        if (existingInterestRate != null) {
            if (existingInterestRate.getLoanTitle().equals(loanTitle)) {
                throw new HandleException("The loan: " + loanTitle + " is already existed.", 409);
            } else if (existingInterestRate.getSavingTitle().equals(savingTitle)) {
                throw new HandleException("The saving: " + savingTitle + " is already existed.",409);
            }
        } else {
            interestRateEntity.setInterestId(generateUniqueId());
            interestRateEntity.setInterestRate(Double.valueOf(interestRate));
            interestRateEntity.setLoanTitle(loanTitle);
            interestRateEntity.setSavingTitle(savingTitle);
            interestRateEntity.setTerm(Integer.parseInt(term));
            create(interestRateEntity);
        }
        return null;
    }

    public InterestRate updateInterestRate(String interestRateId, String interestRate, String loanTitle, String savingTitle, String term) throws HandleException {
        InterestRate interestRateEntityUpdate = new InterestRate();  

        interestRateEntityUpdate.setInterestId(interestRateId);
        interestRateEntityUpdate.setInterestRate(Double.parseDouble(interestRate));
        interestRateEntityUpdate.setLoanTitle(loanTitle);
        interestRateEntityUpdate.setSavingTitle(savingTitle);
        interestRateEntityUpdate.setTerm(Integer.parseInt(term));

        update(interestRateEntityUpdate); 
        return interestRateEntityUpdate;
    }

    public InterestRate checkUpdateInterestRate(String interestRateId, String interestRate, String loanTitle, String savingTitle, String term) throws HandleException {
        InterestRate duplicatedLoanAndSaving = findDuplicated(loanTitle, savingTitle);

        if (duplicatedLoanAndSaving != null){
            if(duplicatedLoanAndSaving.getInterestId().equals(interestRateId)){
                updateInterestRate(interestRateId, interestRate, loanTitle, savingTitle, term);
            }
            else{
                throw new HandleException("The interest rate is already registered.", 409);
            }
        }
        else{
            updateInterestRate(interestRateId, interestRate, loanTitle, savingTitle, term);
        }

        return null;
    }
}
