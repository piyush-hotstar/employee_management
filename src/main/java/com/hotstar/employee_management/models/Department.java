package com.hotstar.employee_management.models;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Component
@Entity
public class Department {

    @Id
    @Column(length = 10)
    private String code;

    private String name;

    @OneToMany
    private List<Employee> employees;

    public Department() {
    }

    public Department(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentCode='" + code + '\'' +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
