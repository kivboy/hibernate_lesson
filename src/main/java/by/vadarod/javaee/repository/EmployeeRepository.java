package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    Long addEmployee(Employee employee);
    List<Employee> getAll();
    void deleteEmployeeById(Long personId);
    Employee findEmployeeById(Long personId);
    void changeEmployee(Employee employee);
    Employee getMinSalaryEmployee();
    Employee getMaxSalaryEmployee();
    Long getAllEmployeesSalary();
}
