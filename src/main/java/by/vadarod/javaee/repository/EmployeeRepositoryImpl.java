package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateSessionFactoryUtil;
import by.vadarod.javaee.entity.Employee;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    private final SessionFactory sessionFactory;

    public EmployeeRepositoryImpl() {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Long addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(employee);
        session.getTransaction().commit();
        session.close();

        return employee.getId();
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select e from Employee e", Employee.class);
        @SuppressWarnings("unchecked")
        List<Employee> employeeList = (List<Employee>)query.getResultList();
        session.close();

        return employeeList;
    }

    @Override
    public void deleteEmployeeById(Long personId) {
        Session session = sessionFactory.openSession();
        Employee foundEmployee = session.get(Employee.class, personId);
        if (foundEmployee != null) {
            session.beginTransaction();
            session.remove(foundEmployee);
            session.getTransaction().commit();
        }
        session.close();
    }

    @Override
    public Employee findEmployeeById(Long personId) {
        Session session = sessionFactory.openSession();
        Employee foundEmployee = session.get(Employee.class, personId);
        session.close();

        return foundEmployee;
    }

    @Override
    public void changeEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Employee foundEmployee = session.get(Employee.class, employee.getId());
        if (foundEmployee != null) {
            session.beginTransaction();
            session.merge(employee);
            session.getTransaction().commit();
        }
        session.close();
    }

    @Override
    public Employee getMinSalaryEmployee() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT e FROM Employee e ORDER BY e.salary LIMIT 1", Employee.class);
        @SuppressWarnings("unchecked")
        List<Employee> employeeList = (List<Employee>)query.getResultList();
        session.close();

        if (!employeeList.isEmpty()) {
            return employeeList.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public Employee getMaxSalaryEmployee() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC LIMIT 1", Employee.class);
        @SuppressWarnings("unchecked")
        List<Employee> employeeList = (List<Employee>)query.getResultList();
        session.close();

        if (!employeeList.isEmpty()) {
            return employeeList.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public Long getAllEmployeesSalary() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT SUM(e.salary) FROM Employee e", Long.class);
        Long salarySum = (Long)query.getSingleResult();
        session.close();

        return salarySum;
    }
}
