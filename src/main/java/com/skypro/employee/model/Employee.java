package com.skypro.employee.model;

public class Employee {
    private static int counter;
    private final int id;
    private String firstName;
    private String lastName;
    private final int department;
    private final int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;

        this.id = counter++;
    }

    public int getId() {return id;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public int getDepartment() {return department;}

    public int getSalary() {return salary;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Сотрудник " +
                "№ " + id +
                ", Имя: " + firstName +
                ", Фамилия: " + lastName +
                ", отдел: " + department +
                ", з/п: " + salary +
                " руб.";
    }
}
