package com.yuravashchenko.spring.springboot.dao;

import com.yuravashchenko.spring.springboot.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final EntityManager entityManager;

    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        /* Using Hibernate */
//        Session session = sessionFactory.getCurrentSession();
//        OR
//        Session session = entityManager.unwrap(Session.class);
//        return session.createQuery("from Employee", Employee.class).getResultList();

        /* Using JPA */
        Query query = entityManager.createQuery("from Employee");
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        /* Using Hibernate */
//        Session session = sessionFactory.getCurrentSession();
//        OR
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(employee);

        /* Using JPA */
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        /* Using Hibernate */
//        Session session = sessionFactory.getCurrentSession();
//        OR
//        Session session = entityManager.unwrap(Session.class);
//        return session.get(Employee.class, id);


        /* Using JPA */
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        /* Using Hibernate */
//        Session session = sessionFactory.getCurrentSession();
//        OR
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("delete from Employee where id = :empId");
//        query.setParameter("empId", id);
//        query.executeUpdate();

        /* Using JPA */
        Query query = entityManager.createQuery("delete from Employee where id = :empId");
        query.setParameter("empId", id);
        query.executeUpdate();
    }
}
