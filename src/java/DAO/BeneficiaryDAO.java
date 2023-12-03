package DAO;

import business.Beneficiary;
import Exception.HandleException;
import business.Customer;
import java.util.List;

public class BeneficiaryDAO extends JpaDAO<Beneficiary> implements GenericDAO<Beneficiary> {

    @Override
    public Beneficiary create(Beneficiary t) {
        return super.create(t);
    }

    @Override
    public Beneficiary get(Object id) {
        return super.find(Beneficiary.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Beneficiary.class, id);

    }

    @Override
    public List<Beneficiary> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

    public Beneficiary findExistingBeneficiary(String accountNumber) {

        List<Beneficiary> beneficiaryList = super.findWithNamedQuery(
                "SELECT b FROM Beneficiary b WHERE b.accountNumber = :accountNumber",
                "accountNumber", accountNumber
        );

        if (!beneficiaryList.isEmpty()) {
            return beneficiaryList.get(0);
        }

        return null;
    }

    public List<Beneficiary> findAllBeneficiary() {

        List<Beneficiary> result = super.findWithNamedQuery("SELECT b FROM Beneficiary b");

        if (!result.isEmpty()) {
            return result;
        }

        return null;
    }

    public List<Beneficiary> findAllBeneficiaryByCustomerId(String customerId) {

        List<Beneficiary> beneficiaryList = super.findWithNamedQuery(
                "SELECT b FROM Beneficiary b WHERE b.customer.customerId = :customerId",
                "customerId", customerId
        );

        if (!beneficiaryList.isEmpty()) {
            return beneficiaryList;
        }

        return null;
    }

    public Beneficiary CreateBeneficiary(String accountNumber, String nickName, Customer customer) throws HandleException {

        Beneficiary beneficiaryEntity = new Beneficiary();
        Beneficiary existingBeneficiary = findExistingBeneficiary(accountNumber);
        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        if (paymentAccountDAO.findExistingPaymentAccount(accountNumber) == null) {
            throw new HandleException("The account with number " + accountNumber
                    + " is not existed.", 409);
        } else {
            
            if (existingBeneficiary != null) {
                if (existingBeneficiary.getAccountNumber().equals(accountNumber)) {
                    throw new HandleException("The Beneficiary with number " + accountNumber
                            + " is already existed.", 409);
                }
            } else {

                beneficiaryEntity.setName(nickName);
                beneficiaryEntity.setAccountNumber(accountNumber);
                beneficiaryEntity.setCustomer(customer);
                create(beneficiaryEntity);

            }
        }

        return null;
    }

}
