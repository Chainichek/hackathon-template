package ru.babim.template.employee.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import ru.babim.template.employee.dto.request.enums.Role;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "employees", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants(asEnum = true)
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID employeeId;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="lastname")
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_removed")
    private Boolean isRemoved;
}
