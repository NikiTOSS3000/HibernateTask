package com.epam.hibernate.model;

import java.util.List;

public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private Address address;
    private List<Position> positionList;

    public Employee(String firstname, String lastname, Address address, List<Position> positionList) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.positionList = positionList;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}