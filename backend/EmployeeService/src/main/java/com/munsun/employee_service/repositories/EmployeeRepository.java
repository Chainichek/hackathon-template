package com.munsun.employee_service.repositories;

import com.munsun.employee_service.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean deleteByLogin(String login);
    boolean existsByLogin(String login);
    Optional<Employee> findByLogin(String login);
}
