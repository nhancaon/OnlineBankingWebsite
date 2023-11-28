package DAO;

import business.Employee;
import Exception.HandleException;
import common.HashGenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAO extends JpaDAO<Employee> implements GenericDAO<Employee> {

    @Override
    public Employee create(Employee t) {
        return super.create(t);
    }

    @Override
    public Employee get(Object id) {
        return super.find(Employee.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Employee.class, id);

    }

    @Override
    public List<Employee> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

}
