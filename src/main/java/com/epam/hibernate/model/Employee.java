package com.epam.hibernate.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_name")
    @SequenceGenerator(name = "seq_name", sequenceName = "employee_sequence")
    private int id;
    
    @Column(name = "FIRSTNAME")
    private String firstname;
    
    @Column(name = "LASTNAME")
    private String lastname;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 100)
    private List<Workplace> workplaceList;

    public Employee(String firstname, String lastname, Address address, List<Workplace> workplaceList) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.workplaceList = workplaceList;
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

    public List<Workplace> getWorkplaceList() {
        return workplaceList;
    }

    public void setWorkplaceList(List<Workplace> positionList) {
        this.workplaceList = positionList;
    }
}