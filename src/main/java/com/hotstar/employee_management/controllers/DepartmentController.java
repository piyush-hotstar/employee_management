package com.hotstar.employee_management.controllers;

import com.hotstar.employee_management.models.Department;
import com.hotstar.employee_management.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {


    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/add")
    public String addDept(@RequestBody Department department) throws Exception {
        return this.departmentService.addDepartment(department);
    }
}
