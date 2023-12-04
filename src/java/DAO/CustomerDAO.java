package DAO;

import business.Customer;
import Exception.HandleException;
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

    public Customer findByCitizenId(String citizenId) {

        List<Customer> result = super.findWithNamedQuery("SELECT c FROM Customer c WHERE c.citizenId = :citizenId",
                "citizenId", citizenId);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public Customer findByEmail(String email) {

        List<Customer> result = super.findWithNamedQuery("SELECT c FROM Customer c WHERE c.email = :email",
                "email", email);

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
                throw new HandleException("The user with Email " + email + " is already registered.", 409);
            } else if (existingCustomer.getCitizenId().equals(citizenId)) {
                throw new HandleException("The user with Citizen Identity " + citizenId + " is already registered.",409);
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

            String to = email;
            String subject = "Welcome to NND Banking";
            String body = "Dear " + fullName + ",\n\n"
                    + "Thank you for creating an account with us. Your account is ready for use. "
                    + "You can now start using our services at NND Banking.\n\n"
                    + "If you have any questions about our products or services, please feel free to contact us at any time.\n\n"
                    + "Sincerely,\n\n" + "NND Banking";
            try {
                MailSender.sendMail(to, subject, body);
            } catch (MessagingException ex) {
                Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            create(customerEntity);
        }

        return null;
    }

    public Customer updateCustomer(String customerId, String fullName, String email, String password, String citizenId, String phoneNumber, String dateOfBirth, String address, int pinNumber) throws HandleException {
        Customer customerEntityUpdate = new Customer();
        String encryptedPassword = HashGenerator.generateMD5(password);   

        customerEntityUpdate.setCustomerId(customerId);
        customerEntityUpdate.setName(fullName);
        customerEntityUpdate.setEmail(email);
        customerEntityUpdate.setPassword(encryptedPassword);
        customerEntityUpdate.setPhoneNumber(phoneNumber);
        customerEntityUpdate.setDateofBirth(LocalDate.parse(dateOfBirth));
        customerEntityUpdate.setCitizenId(citizenId);
        customerEntityUpdate.setAddress(address);
        customerEntityUpdate.setPinNumber(pinNumber);

        String to = email;
        String subject = "Update Notification from NND Banking";
        String body = "Dear " + fullName + ",\n\n"
                + "We're glad to announce that your account has been successfully updated. "
                + "You can now start to logging in website with new updated information.\n\n"
                + "If you have any questions about our products or services, please feel free to contact us at any time.\n\n"
                + "Sincerely,\n\n" + "NND Banking";
        try {
            MailSender.sendMail(to, subject, body);
        } catch (MessagingException ex) {
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        update(customerEntityUpdate); 
        return customerEntityUpdate;
    }

    public Customer checkUpdateCustomer(String customerId, String citizenId, String email, String fullName, String password,  String phoneNumber, String dateOfBirth, String address, int pinNumber) throws HandleException {
        Customer duplicatedEmailCitizenId = findByEmailOrCitizenId(email, citizenId);

        if (duplicatedEmailCitizenId != null){
            if(duplicatedEmailCitizenId.getCustomerId().equals(customerId)){
                updateCustomer(customerId, fullName, email, password, citizenId, phoneNumber, dateOfBirth, address, pinNumber);
            }
            else{
                throw new HandleException("The user with Email: " + email + " and Citizen ID: " + citizenId 
                + " is already registered.", 409);
            }
        }
        else{
            updateCustomer(customerId, fullName, email, password, citizenId, phoneNumber, dateOfBirth, address, pinNumber);
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

    public Customer RecoverPassword(String email) throws HandleException {

        Customer existingCustomer = findByEmail(email);

        if (existingCustomer == null) {
            throw new HandleException("Cannot find user with email " + email, 409);
        } else {

            String recoveredPassword = generateUniqueId();
            String encryptedNewPassword = HashGenerator.generateMD5(recoveredPassword);
            existingCustomer.setPassword(encryptedNewPassword);
            update(existingCustomer);
            String to = email;
            String subject = "Welcome to NND Banking";
            String body = "Thank you for using our services. Here is your recover password " + recoveredPassword
                    + "\nYou can use that password to access to our services at NND Banking.\n\n"
                    + "If you have any questions about our products or services, please feel free to contact us at any time.\n\n"
                    + "Sincerely,\n\n" + "NND Banking";
            try {
                MailSender.sendMail(to, subject, body);
            } catch (MessagingException ex) {
                Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return null;
    }
    

}
