package com.epam.hibernate.model;

import java.util.List;

public class Country {
    private int id;
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Country() {
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
}
