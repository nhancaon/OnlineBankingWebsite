package DAO;

import business.Loan;
import Exception.CreateException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class LoanDAO extends JpaDAO<Loan> implements GenericDAO<Loan> {

    @Override
    public Loan create(Loan t) {
        return super.create(t);
    }

    @Override
    public Loan get(Object id) {
        return super.find(Loan.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Loan.class, id);

    }

    @Override
    public List<Loan> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

}
