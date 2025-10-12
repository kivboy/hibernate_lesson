package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Employee;
import by.vadarod.javaee.repository.EmployeeRepository;
import by.vadarod.javaee.repository.EmployeeRepositoryImpl;
import lombok.NonNull;

import java.util.List;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepositoryImpl repository) {
        this.employeeRepository = repository;
    }

    public Long addEmployee(@NonNull Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAll();
    }

    public void deleteEmployee(@NonNull Employee employee) {
        employeeRepository.deleteEmployeeById(employee.getId());
    }

    public Employee findEmployeeById(Long personId) {
        return employeeRepository.findEmployeeById(personId);
    }
}
