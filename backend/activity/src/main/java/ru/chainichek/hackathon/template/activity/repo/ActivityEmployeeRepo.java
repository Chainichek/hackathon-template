package ru.chainichek.hackathon.template.activity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chainichek.hackathon.template.activity.model.activity.ActivityEmployee;

public interface ActivityEmployeeRepo extends JpaRepository<ActivityEmployee, ActivityEmployee.ActivityEmployeeKey> {
}
