package com.hotstar.employee_management.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

enum Designation {
    P1,
    P2,
    P3,
    P4,
    P5
}

@Component
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @Column(unique = true)
    private String emailID;

    private LocalDate doj;
    private Integer compensation;

    private Designation designation;

    @ManyToOne
    private Department department;

    private String manager;

    private LocalDate lwd;

    public Employee() {}

    public Employee(String name, String emailID, LocalDate doj, Integer compensation, Designation designation, String manager,
                    Department department
    ) {
        this.name = name;
        this.emailID = emailID;
        this.doj = doj;
        this.compensation = compensation;
        this.department = department;
        this.designation = designation;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public Integer getCompensation() {
        return compensation;
    }

    public void setCompensation(Integer compensation) {
        this.compensation = compensation;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public LocalDate getLwd() {
        return lwd;
    }

    public void setLwd(LocalDate lwd) {
        this.lwd = lwd;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailID='" + emailID + '\'' +
                ", doj=" + doj +
                ", compensation=" + compensation +
                ", designation=" + designation +
                ", department=" + department +
                ", manager='" + manager + '\'' +
                ", lwd=" + lwd +
                '}';
    }
}
