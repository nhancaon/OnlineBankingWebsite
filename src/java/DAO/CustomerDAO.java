package DAO;

import business.Customer;
import Exception.HandleException;
import common.HashGenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

    @Override
    public Customer create(Customer t) {
        return super.create(t);
    }

    @Override
    public Customer get(Object id) {
        return super.find(Customer.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Customer.class, id);

    }

    @Override
    public List<Customer> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }


    public List<Customer> findAllCustomer() {

        List<Customer> result = super.findWithNamedQuery("SELECT c FROM Customer c");

        if (!result.isEmpty()) {
            return result;
        }

        return null;
    }

    public Customer findByCustomerId(String customerId) {

        List<Customer> result = super.findWithNamedQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId",
                "customerId", customerId);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public Customer findByEmailOrCitizenId(String email, String citizenId) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("citizenId", citizenId);

        List<Customer> result = super.findWithNamedQuery(
                "SELECT c FROM Customer c WHERE c.email = :email OR c.citizenId = :citizenId", parameters);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public Customer checkLogin(String email, String password) {

        Map<String, Object> parameters = new HashMap<>();
        String encryptedPassword = HashGenerator.generateMD5(password);
        parameters.put("email", email);
        parameters.put("password", encryptedPassword);

        List<Customer> result = super.findWithNamedQuery("SELECT c FROM Customer c "
                + "WHERE c.email = :email AND c.password = :password", parameters);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public Customer customerSignup(String fullName, String email, String password, String citizenId,
            String phoneNumber, String dateOfBirth, String address, int pinNumber) throws HandleException {

        Customer customerEntity = new Customer();
        Customer existingCustomer = findByEmailOrCitizenId(email, citizenId);
        String encryptedPassword = HashGenerator.generateMD5(password);
        if (existingCustomer != null) {
            if (existingCustomer.getEmail().equals(email)) {
                throw new HandleException("The user with Email " + email
                        + " is already registered.", 409);
            } else if (existingCustomer.getCitizenId().equals(citizenId)) {
                throw new HandleException("The user with Citizen Identity " + citizenId + " is already registered.",
                        409);
            }
        } else {

            customerEntity.setCustomerId(generateUniqueId());
            customerEntity.setName(fullName);
            customerEntity.setEmail(email);
            customerEntity.setPassword(encryptedPassword);
            customerEntity.setPhoneNumber(phoneNumber);
            customerEntity.setDateofBirth(LocalDate.parse(dateOfBirth));
            customerEntity.setCitizenId(citizenId);
            customerEntity.setAddress(address);
            customerEntity.setPinNumber(pinNumber);
            create(customerEntity);
        }

        return null;
    }

    public Customer ChangePassword(String customerId, String currentPassword,
            String newPassword, String confirmPassword) throws HandleException {

        Customer existingCustomer = findByCustomerId(customerId);

        if (existingCustomer == null) {
            throw new HandleException("Cannot find user with ID " + customerId, 409);
        }

        String encryptedPassword = HashGenerator.generateMD5(currentPassword);

        if (encryptedPassword.equals(existingCustomer.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                String encryptedNewPassword = HashGenerator.generateMD5(newPassword);
                existingCustomer.setPassword(encryptedNewPassword);
                update(existingCustomer);
            } else {
                throw new HandleException("The confirm password is incorrect. Please try again", 409);
            }
        } else {
            throw new HandleException("The current password is incorrect. Please check again", 409);
        }

        return null;

    }

}
