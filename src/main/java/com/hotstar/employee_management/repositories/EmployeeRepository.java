package com.hotstar.employee_management.repositories;

import com.hotstar.employee_management.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select emp from Employee emp where emp.emailID=?1")
    public Employee findByEmailId(String email);

    @Query("select emp from Employee emp where emp.manager=?1")
    public List<Employee> findAllEmployeeUnderManager(String email);

    @Query("select emp from Employee emp where emp.lwd is null")
    public List<Employee> findAllCurrentEmployee();

}
