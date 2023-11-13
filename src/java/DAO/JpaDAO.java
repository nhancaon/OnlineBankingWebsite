package DAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaDAO {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("testPU");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}