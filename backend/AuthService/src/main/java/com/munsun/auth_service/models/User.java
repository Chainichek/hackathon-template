package com.munsun.auth_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
}
