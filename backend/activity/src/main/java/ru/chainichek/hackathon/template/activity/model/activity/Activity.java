package ru.chainichek.hackathon.template.activity.model.activity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.chainichek.hackathon.template.activity.dto.activity.ActivityStatusHistoryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.DESCRIPTION_MAX_LENGTH;
import static ru.chainichek.hackathon.template.activity.validation.value.ActivityValidationValue.TITLE_MAX_LENGTH;

@Getter
@Setter
@Entity
@Table(name = "activity", schema = "activity")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = TITLE_MAX_LENGTH, nullable = false)
    private String title;

    @Column(length = DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(nullable = false)
    private String author;

    @OneToMany(mappedBy = "activity", orphanRemoval = true)
    private Set<ActivityEmployee> employees;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

    @Type(JsonType.class)
    private List<ActivityStatusHistoryDto> statusHistory;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
