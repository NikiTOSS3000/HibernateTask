package com.epam.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "WORKPLACE")
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gen")
    @SequenceGenerator(name = "seq_name", sequenceName = "workplace_sequence")
    private int id;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "workplace")
    private Position position;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Office office;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    public Workplace() {
    }

    public Workplace(Position position, Office office, Employee employee) {
        this.position = position;
        this.office = office;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
