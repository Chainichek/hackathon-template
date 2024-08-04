package com.munsun.employee_service.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "groups", schema = "employee")
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants(asEnum = true)
public class EmployeeGroup {
    @Id
    @Column(name = "groupId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID groupId;

    @Column(name="title")
    String title;

    @Column(name="description")
    String description;

    @ManyToMany
    @JoinTable(
            name = "groups_employees",
            schema = "employee",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
    List<Employee> employees;

    @Column(name="is_removed")
    private Boolean isRemoved;
}
