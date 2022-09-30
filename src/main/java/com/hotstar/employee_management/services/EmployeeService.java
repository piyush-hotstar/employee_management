package com.hotstar.employee_management.services;

import com.hotstar.employee_management.models.Designation;
import com.hotstar.employee_management.models.Employee;
import com.hotstar.employee_management.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"employee"})
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String isAllGood() {
        return "All good!";
    }

    @Cacheable()
    public Employee checkIfEmployeeExists(String email) {
//        System.out.println(email);
        System.out.println("checking for emp");
        Employee emp1 = this.employeeRepository.findByEmailId(email);
        if(emp1 == null || emp1.getLwd() != null) {
            LOG.error("user not found", email);
            return null;
        }
//        System.out.println(emp1.toString());
        return emp1;
    }

    @CachePut()
    public String addEmployee(Employee emp) {
        if(emp.getDesignation() != Designation.P5) {
            if (checkIfEmployeeExists(emp.getManager()) == null) {
                return "Manager is not currently employed with this company!";
            }
        }
        Employee employee = new Employee(emp.getName(), emp.getEmailID(), LocalDate.now(), emp.getCompensation(),
                emp.getDesignation(), emp.getManager(), emp.getDepartment());
        if(employee.getManager() == null)
            return "Manager is necessary unless you are on P5 level";
        this.employeeRepository.save(employee);
        LOG.info("adding the emp", employee);
        return employee.toString();
    }

    public List<Employee> findAllEmployeesUnderGivenManager(String email) {
        return this.employeeRepository.findAllEmployeeUnderManager(email);
    }

    @CacheEvict(value = "employee")
    public String removeEmployee(String emailID) {
//        System.out.println(emailID);
        Employee employee = checkIfEmployeeExists(emailID);
        if (employee == null) {
            return "No employee found with given email";
        }
        List<Employee> potentialEmps = findAllEmployeesUnderGivenManager(emailID);
        if (potentialEmps.size() != 0) {
            return "Given employee is manager of " + potentialEmps.size() + " employee(s), First assign them to someone else";
        }
        employee.setLwd(LocalDate.now());
        this.employeeRepository.save(employee);
        LOG.info("removing user", emailID);
        return employee.toString();
    }

    public String empDetails(String emailID) {
        LOG.info("checking from DB", emailID);
        Employee employee = checkIfEmployeeExists(emailID);
        return employee == null ? "No employee with given emailID exists" : employee.toString();
    }

    @CacheEvict(value = "employee")
    public String promoteEmployee(Employee emp) {
        Employee employee = checkIfEmployeeExists(emp.getEmailID());
        LOG.info("user to be promoted", emp);
        if (employee == null) {
            return "No employee found with given email";
        }
        if(employee.getDesignation() == Designation.P4) {
            employee.setDesignation(Designation.P5);
        } else if(employee.getDesignation() == Designation.P3) {
            employee.setDesignation(Designation.P4);
        } else if(employee.getDesignation() == Designation.P2) {
            employee.setDesignation(Designation.P3);
        } else if(employee.getDesignation() == Designation.P1) {
            employee.setDesignation(Designation.P2);
        }
        employee.setCompensation(emp.getCompensation());
        this.employeeRepository.save(employee);
        LOG.info("user after promotion", employee);
        return employee.toString();
    }

    @CacheEvict(value = "employee")
    public String changeManager(Employee emp) {
        Employee employee = checkIfEmployeeExists(emp.getEmailID());
        if (employee == null) {
            return "No employee found with given email";
        }
        Employee manager = checkIfEmployeeExists(emp.getManager());
        if (manager == null) {
            return "No Manager(emp) found with given email";
        }
        employee.setManager(emp.getManager());
        this.employeeRepository.save(employee);
        LOG.info("new manager", employee);
        return employee.toString();
    }

    @Cacheable
    public StringBuilder getAllCurrentEmployees() {
        List<Employee> employees = this.employeeRepository.findAllCurrentEmployee();
        StringBuilder s = new StringBuilder();
        for (Employee e:
             employees) {
            s.append(e.toString());
        }
        return s;
    }
}
