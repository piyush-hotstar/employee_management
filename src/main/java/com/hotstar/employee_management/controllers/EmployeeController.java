package com.hotstar.employee_management.controllers;

import com.hotstar.employee_management.models.Employee;
import com.hotstar.employee_management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
//    private final CacheManager cacheManager;

    @Autowired
    public EmployeeController(EmployeeService employeeService
//            , CacheManager cacheManager
            ) {
        this.employeeService = employeeService;
//        this.cacheManager = cacheManager;
    }


    @GetMapping
    public String healthCheck() {
        return employeeService.isAllGood();
    }

    @GetMapping("/all")
    public StringBuilder getAllEmp() {
        return this.employeeService.getAllCurrentEmployees();
    }

    @PostMapping("/onboard")
    public String addEmp(@RequestBody Employee employee) {
        return this.employeeService.addEmployee(employee);
    }

    @PostMapping("/offboard")
    public String removeEmp(@RequestBody Map<String,String> body) {
        return this.employeeService.removeEmployee(body.get("emailID"));
    }

    @PostMapping("/details")
    @Cacheable(value = "employee")
    public String empDetails(@RequestBody Map<String,String> body) {
//        System.out.println(cacheManager.get);
        return this.employeeService.empDetails(body.get("emailID"));
    }

    @PostMapping("/promote")
    public String promoteEmp(@RequestBody Employee employee) {
        return this.employeeService.promoteEmployee(employee);
    }

    @PostMapping("/manager")
    public String changeManager(@RequestBody Employee employee) {
        return this.employeeService.changeManager(employee);
    }

}
