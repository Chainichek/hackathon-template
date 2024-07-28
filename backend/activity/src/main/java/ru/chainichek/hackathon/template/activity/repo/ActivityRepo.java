package ru.chainichek.hackathon.template.activity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chainichek.hackathon.template.activity.model.Activity;

import java.util.UUID;

public interface ActivityRepo extends JpaRepository<Activity, UUID> {
}
