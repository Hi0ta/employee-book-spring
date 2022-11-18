package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
      if(!StringUtils.isAlpha(employeeRequest.getFirstName()) || !StringUtils.isAlpha(employeeRequest.getLastName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());

        employee.setFirstName(StringUtils.capitalize(employee.getFirstName()));
        employee.setLastName(StringUtils.capitalize(employee.getLastName()));

        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public OptionalInt getSalaryMin() {
       return employees.values().stream()
                .mapToInt(Employee::getSalary)
               .min();
    }

    public OptionalInt getSalaryMax() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .max();
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
