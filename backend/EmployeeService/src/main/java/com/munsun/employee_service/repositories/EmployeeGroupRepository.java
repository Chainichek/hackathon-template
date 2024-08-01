package com.munsun.employee_service.repositories;

import com.munsun.employee_service.models.EmployeeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeGroupRepository extends JpaRepository<EmployeeGroup, UUID> {
}
