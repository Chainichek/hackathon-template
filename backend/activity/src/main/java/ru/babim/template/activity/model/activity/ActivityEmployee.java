package ru.babim.template.activity.model.activity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "activity_employee", schema = "activity")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ActivityEmployee {

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class ActivityEmployeeKey implements Serializable {
        @Column(name = "activity_id")
        private UUID activityId;

        @Column(name = "employee_login")
        private String employeeLogin;
    }

    @EmbeddedId
    private ActivityEmployeeKey id;

    @ManyToOne
    @MapsId("activityId")
    @JoinColumn
    private Activity activity;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.UNCHECKED;
}