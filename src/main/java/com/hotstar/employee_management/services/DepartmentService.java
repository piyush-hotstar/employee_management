package com.hotstar.employee_management.services;

import com.hotstar.employee_management.models.Department;
import com.hotstar.employee_management.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String addDepartment(Department dept) throws Exception {
        Department department = new Department(dept.getCode(), dept.getName());
        this.departmentRepository.save(department);
        return department.toString();
    }

}
