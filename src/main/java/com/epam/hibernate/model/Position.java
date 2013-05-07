package com.epam.hibernate.model;

public class Position {
    private int id;
    private String name;
    private Office office;
    private Employee employee;

    public Position(String name, Office office, Employee employee) {
        this.name = name;
        this.office = office;
        this.employee = employee;
    }

    public Position() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
