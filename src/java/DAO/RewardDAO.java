package DAO;

import business.Reward;
import Exception.SignupException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RewardDAO extends JpaDAO<Reward> implements GenericDAO<Reward> {

    @Override
    public Reward create(Reward t) {
        return super.create(t);
    }

    @Override
    public Reward get(Object id) {
        return super.find(Reward.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Reward.class, id);

    }

    @Override
    public List<Reward> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

}
