package com.epam.hibernate.model;

import java.util.List;

class City {
    private int id;
    private String name;
    private List<Address> addressList;
    private Country country;
}
