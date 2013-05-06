package com.epam.hibernate.model;

import java.util.LinkedList;
import java.util.List;

public class City {
    private int id;
    private String name;
    private Country country;
    private List<Address> addressList;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
        addressList = new LinkedList<Address>();
    }

    public City() {
        addressList = new LinkedList<Address>();
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

}
