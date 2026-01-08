package com.resolveit.config;

import com.resolveit.model.Department;
import com.resolveit.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadDepartments(DepartmentRepository departmentRepo) {
        return args -> {

            List<String> departments = List.of(
                    "Hostel",
                    "Library",
                    "IT",
                    "Maintenance",
                    "Administration"
            );

            for (String name : departments) {
                departmentRepo.findByName(name)
                        .orElseGet(() -> departmentRepo.save(new Department(name)));
            }

            System.out.println("✅ Departments loaded");
        };
    }
}
