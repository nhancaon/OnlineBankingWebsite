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

    public Customer findByEmail(String email, String citizenId) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("citizenId", citizenId);

        List<Customer> result = super.findWithNamedQuery("SELECT c FROM Customer c WHERE c.email = :email OR c.citizenId = :citizenId", parameters);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public Customer ChangePassword(String currentPassword, String newPassword, String confirmPassword) {

        // Map<String, Object> parameters = new HashMap<>();
        // parameters.put("currentPassword", currentPassword);
        // parameters.put("newPassword", newPassword);
        // parameters.put("confirmPassword", confirmPassword);

        // List<Customer> result = super.findWithNamedQuery("SELECT c FROM Customer c WHERE c.password = :currentPassword", parameters);

        // if (!result.isEmpty()) {
        //     return result.get(0);
        // }

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
        Customer existingCustomer = findByEmail(email, citizenId);
        String encryptedPassword = HashGenerator.generateMD5(password);
        if (existingCustomer != null) {
            if (existingCustomer.getEmail().equals(email)) {
                throw new HandleException("The user with Email " + email
                        + " is already registered.", 409);
            } else if (existingCustomer.getCitizenId().equals(citizenId)) {
                throw new HandleException("The user with Citizen Identity " + citizenId
                        + " is already registered.", 409);
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

}
