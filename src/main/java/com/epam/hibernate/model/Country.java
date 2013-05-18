package com.epam.hibernate.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class Country {

    private int id;
    private String name;
    private List<City> cityList;

    public Country(String name, List<City> cityList) {
        this.name = name;
        this.cityList = cityList;
    }

    public Country() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gen")
    @SequenceGenerator(name = "seq_name", sequenceName = "country_sequence")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
