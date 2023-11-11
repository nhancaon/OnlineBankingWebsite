package data;

import business.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CustomerDB {


    public static Customer customerLogin(String email,String password) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
         String qString = "SELECT email, password FROM Customer c "
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
