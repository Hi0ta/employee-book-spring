package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null){
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public List<Employee> getSalaryMin() {
        int min = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .min().getAsInt();
        return employees.values().stream()
                .filter(e -> e.getSalary() == min).collect(Collectors.toList());
    }

    public List<Employee> getSalaryMax() {
        int max = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .max().getAsInt();
        return employees.values().stream()
                .filter(e -> e.getSalary() == max).collect(Collectors.toList());
    }

    public List<Employee> getHighSalary() {
        int sum = employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
        int high = sum/(employees.size() + 1);
        return employees.values().stream()
                .filter(e -> e.getSalary() > high).collect(Collectors.toList());
    }
}
