package DAO;

import Exception.HandleException;
import business.Customer;
import business.PaymentAccount;
import business.Reward;
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

public class PaymentAccountDAO extends JpaDAO<PaymentAccount> implements GenericDAO<PaymentAccount> {

    @Override
    public PaymentAccount create(PaymentAccount t) {
        return super.create(t);
    }

    @Override
    public PaymentAccount get(Object id) {
        return super.find(PaymentAccount.class, id);
    }

    @Override
    public PaymentAccount update(PaymentAccount t) {
        return super.update(t);
    }

    @Override
    public void delete(Object id) {
        super.delete(PaymentAccount.class, id);

    }

    @Override
    public List<PaymentAccount> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

    public List<PaymentAccount> findAllPaymentAccount() {

        List<PaymentAccount> result = super.findWithNamedQuery("SELECT pa FROM PaymentAccount pa");

        if (!result.isEmpty()) {
            return result;
        }
        return null;

    }

    public PaymentAccount findByPaymentAccountId(String paymentAccountId) {

        List<PaymentAccount> result = super.findWithNamedQuery("SELECT p FROM PaymentAccount p WHERE p.paymentAccountId = :paymentAccountId",
                "paymentAccountId", paymentAccountId);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public PaymentAccount setDefaultPaymentAccount(String customerId, String accountNumber) {

        PaymentAccount previousDefaultPaymentAccount = findDefaultPaymentAccount(customerId);
        previousDefaultPaymentAccount.setAccountStatus("Active");
        update(previousDefaultPaymentAccount);

        PaymentAccount currentPaymentAccount = findExistingPaymentAccount(accountNumber);
        currentPaymentAccount.setAccountStatus("Default");
        update(currentPaymentAccount);

        return null;
    }

    public PaymentAccount checkDefaultUpdate(String accountNumber, String customerId){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", customerId);
        parameters.put("accountNumber", accountNumber);
        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.customer.customerId = :customerId AND pa.accountNumber = :accountNumber",
                parameters);

        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList.get(0);
        }

        return null;
    }

    public PaymentAccount findDefaultPaymentAccount(String customerId) {

        String accountStatus = "Default";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", customerId);
        parameters.put("accountStatus", accountStatus);
        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.customer.customerId = :customerId AND pa.accountStatus = :accountStatus",
                parameters);

        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList.get(0);
        }

        return null;
    }

    public List<PaymentAccount> findPaymentAccountByCusId(String customerId) {

        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.customer.customerId = :customerId ORDER BY pa.accountStatus DESC",
                "customerId",
                customerId);
        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList;
        }

        return null;
    }

    public PaymentAccount findExistingPaymentAccount(String accountNumber) {

        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.accountNumber = :accountNumber",
                "accountNumber", accountNumber);

        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList.get(0);
        }

        return null;
    }

    public List<Reward> findRewardOfAccount(String accountNumber) {

        List<PaymentAccount> paymentAccountList = super.findWithNamedQuery(
                "SELECT pa FROM PaymentAccount pa WHERE pa.accountNumber = :accountNumber",
                "accountNumber", accountNumber);

        if (!paymentAccountList.isEmpty()) {
            return paymentAccountList.get(0).getRewards();
        }

        return null;
    }

    public PaymentAccount CreatePaymentAccount(Customer customer, String accountNumber) throws HandleException {
        PaymentAccount paymentAccountEntity = new PaymentAccount();
        PaymentAccount existingPaymentAccount = findExistingPaymentAccount(accountNumber);
        PaymentAccount existingDefaultPaymentAccount = findDefaultPaymentAccount(customer.getCustomerId());
        if (existingPaymentAccount != null) {
            if (existingPaymentAccount.getAccountNumber().equals(accountNumber)) {
                throw new HandleException("The Payment Account " + accountNumber + " is already existed.", 409);
            }
        } else {
            if (existingDefaultPaymentAccount != null) {
                paymentAccountEntity.setAccountStatus("Active");
            } else {
                paymentAccountEntity.setAccountStatus("Default");
            }
            paymentAccountEntity.setPaymentAccountId(generateUniqueId());
            paymentAccountEntity.setCustomer(customer);
            paymentAccountEntity.setCurrentBalence(0.0);
            paymentAccountEntity.setRewardPoint(0);
            paymentAccountEntity.setAccountNumber(accountNumber);
            paymentAccountEntity.setAccountType("Classic");

            create(paymentAccountEntity);
        }
        return null;
    }

    public PaymentAccount updatePaymentAccount(String paymentAccountId, String accountNumber, String accountStatus, String accountType, String currentBalance, String rewardPoint, String customerId) throws HandleException {
        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();
        PaymentAccount paymentAccountEntityUpdate = paymentAccountDAO.findByPaymentAccountId(paymentAccountId);
        PaymentAccount existingDefault = paymentAccountDAO.checkDefaultUpdate(accountNumber, paymentAccountEntityUpdate.getCustomer().getCustomerId());

        List<PaymentAccount> paymentAccountList = paymentAccountDAO.findPaymentAccountByCusId(customerId);

        if(accountStatus.equals("Default")){
            if(existingDefault.getAccountStatus().equals(accountStatus)){
                paymentAccountEntityUpdate.setPaymentAccountId(paymentAccountId);
                paymentAccountEntityUpdate.setAccountNumber(accountNumber);
                paymentAccountEntityUpdate.setAccountStatus(accountStatus);
                paymentAccountEntityUpdate.setAccountType(accountType);
                paymentAccountEntityUpdate.setCurrentBalence(Double.parseDouble(currentBalance));
                paymentAccountEntityUpdate.setRewardPoint(Integer.parseInt(rewardPoint));
                System.out.println("1");
            }
            else{
                System.out.println("2");
                for(int i = 0; i < paymentAccountList.size(); i++){
                    if(!accountNumber.equals(paymentAccountList.get(i).getAccountNumber()) && paymentAccountList.get(i).getAccountStatus().equals("Default")){
                        paymentAccountList.get(i).setAccountStatus("Active");
                        System.out.println("3");
                        paymentAccountEntityUpdate.setPaymentAccountId(paymentAccountId);
                        paymentAccountEntityUpdate.setAccountNumber(accountNumber);
                        paymentAccountEntityUpdate.setAccountStatus(accountStatus);
                        paymentAccountEntityUpdate.setAccountType(accountType);
                        paymentAccountEntityUpdate.setCurrentBalence(Double.parseDouble(currentBalance));
                        paymentAccountEntityUpdate.setRewardPoint(Integer.parseInt(rewardPoint));
                        update(paymentAccountList.get(i));
                        break;
                    }
                }
            }
        }
        else{
            if(!existingDefault.getAccountStatus().equals(accountStatus)){
                System.out.println("4");
                throw new HandleException("This default account is not allowed to modified status without necessary changes.", 409);
            }
            else{
                System.out.println("5");
                paymentAccountEntityUpdate.setPaymentAccountId(paymentAccountId);
                paymentAccountEntityUpdate.setAccountNumber(accountNumber);
                paymentAccountEntityUpdate.setAccountStatus(accountStatus);
                paymentAccountEntityUpdate.setAccountType(accountType);
                paymentAccountEntityUpdate.setCurrentBalence(Double.parseDouble(currentBalance));
                paymentAccountEntityUpdate.setRewardPoint(Integer.parseInt(rewardPoint));
            } 
        }
        update(paymentAccountEntityUpdate); 
        return paymentAccountEntityUpdate;
    }


    public PaymentAccount checkUpdatePaymentAccount(String paymentAccountId, String accountNumber, String accountStatus, String accountType, String currentBalance, String rewardPoint, String customerId) throws HandleException {
        PaymentAccount duplicatedAccountNumber = findExistingPaymentAccount(accountNumber);

        if (duplicatedAccountNumber != null){
            if(duplicatedAccountNumber.getPaymentAccountId().equals(paymentAccountId)){
                updatePaymentAccount(paymentAccountId, accountNumber, accountStatus, accountType, currentBalance, rewardPoint, customerId);
            }
            else{
                throw new HandleException("The payment account with account number: " + accountNumber +" is already registered.", 409);
            }
        }
        else{
            updatePaymentAccount(paymentAccountId, accountNumber, accountStatus, accountType, currentBalance, rewardPoint, customerId);
        }

        return null;
    }

}
