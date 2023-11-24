package DAO;

import business.Beneficiary;
import Exception.HandleException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

}
