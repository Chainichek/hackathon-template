package ru.babim.template.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.babim.template.employee.models.EmployeeGroup;

import java.util.UUID;

public interface EmployeeGroupRepository extends JpaRepository<EmployeeGroup, UUID> {
}
