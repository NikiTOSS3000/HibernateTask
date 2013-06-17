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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "OFFICE")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_name")
    @SequenceGenerator(name = "seq_name", sequenceName = "office_sequence")
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    
    @OneToMany(mappedBy = "office",cascade = CascadeType.ALL)
    private List<Workplace> workplaceList;
    
    @Formula("(select count(w.id) from workplace w where w.office_id=id)")
    private int employeesCount;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    public Office(Address address, List<Workplace> workplaceList, Company company) {
        this.address = address;
        this.workplaceList = workplaceList;
        this.company = company;
    }

    public Office() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
