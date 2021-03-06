package com.epam.hibernate.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_name")
    @SequenceGenerator(name = "seq_name", sequenceName = "country_sequence")
    private int id;
    
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cityList;

    public Country(String name, List<City> cityList) {
        this.name = name;
        this.cityList = cityList;
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

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
