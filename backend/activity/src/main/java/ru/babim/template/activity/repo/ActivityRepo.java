package ru.babim.template.activity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.babim.template.activity.model.activity.Activity;
import ru.babim.template.activity.model.activity.ActivityStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ActivityRepo extends JpaRepository<Activity, UUID> {
    @Query("""
            SELECT a FROM Activity a JOIN a.employees e WHERE e.id.employeeLogin = :employeeLogin
                        AND (:status IS NULL OR a.status = :status)
                        AND (:startAt IS NULL OR a.startAt >= :startAt)
                        AND (:endAt IS NULL OR a.endAt <= :endAt)
            """)
    List<Activity> findByEmployeeLoginAndStatusAndDateRange(
            @Param("employeeLogin") String employeeLogin,
            @Param("status") ActivityStatus status,
            @Param("startAt") LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt
    );
}
