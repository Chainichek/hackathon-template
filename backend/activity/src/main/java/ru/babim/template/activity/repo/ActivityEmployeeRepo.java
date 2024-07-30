package ru.babim.template.activity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.babim.template.activity.model.activity.ActivityEmployee;

public interface ActivityEmployeeRepo extends JpaRepository<ActivityEmployee, ActivityEmployee.ActivityEmployeeKey> {
}
