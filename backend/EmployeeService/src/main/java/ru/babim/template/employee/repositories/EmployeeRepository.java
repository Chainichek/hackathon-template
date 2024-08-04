package ru.babim.template.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.babim.template.employee.models.Employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean deleteByLogin(String login);
    boolean existsByLogin(String login);
    Optional<Employee> findByLogin(String login);
}
