package data;

import business.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CustomerDB {

//    public static void insert(userhaha user) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.persist(user);
//            trans.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//    }

//    public static void update(userhaha user) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.merge(user);
//            trans.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//    }
//
//    public static void delete(userhaha user) {
//        EntityManager em = DBUtil.getEmFactory().createEntityManager();
//        EntityTransaction trans = em.getTransaction();
//        trans.begin();
//        try {
//            em.remove(em.merge(user));
//            trans.commit();
//        } catch (Exception e) {
//            System.out.println(e);
//            trans.rollback();
//        } finally {
//            em.close();
//        }
//    }

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
