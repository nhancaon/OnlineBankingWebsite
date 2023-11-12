package data;

import business.Customer;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CustomerDB {

    public static Customer customerLogin(String email, String password) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Customer c "
                + "WHERE c.email = :email AND c.password = :password";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        q.setParameter("email", email);
        q.setParameter("password", password);
        Customer customer = null;
        try {
            customer = q.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return (Customer) customer;
    }

    public static void customerSignup(String fullName, String email, String password, String citizenIdentity,
            String phoneNumber, String dateOfBirth, String address) {

        System.out.println(fullName);
        System.out.println(email);
        System.out.println(password);
        System.out.println(phoneNumber);
        System.out.println(citizenIdentity);
        System.out.println(dateOfBirth);
        System.out.println(address);
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();

            Customer customerEntity = new Customer();
//            customerEntity.setCustomerId(1);
            customerEntity.setName(fullName);
            customerEntity.setCustomerType("Regular");
            customerEntity.setEmail(email);
            customerEntity.setPassword(password);
            customerEntity.setPhoneNumber(phoneNumber);
            customerEntity.setLocalDateofBirth(LocalDate.parse(dateOfBirth));
//            customerEntity.setCitizenIdentity(citizenIdentity);
            customerEntity.setAddress(address);

            em.persist(customerEntity);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static Customer selectUser(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM userhaha u "
                + "WHERE u.email = :email";
        TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
        q.setParameter("email", email);
        Customer user = null;
        try {
            user = q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return (Customer) user;
    }
}
