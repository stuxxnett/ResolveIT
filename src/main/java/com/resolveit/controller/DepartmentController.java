package com.resolveit.controller;

import com.resolveit.model.Department;
import com.resolveit.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentRepository departmentRepo;

    public DepartmentController(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }
}
