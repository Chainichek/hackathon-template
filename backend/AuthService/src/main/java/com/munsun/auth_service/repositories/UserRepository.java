package com.munsun.auth_service.repositories;

import com.munsun.auth_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByAccount_Login(String login);
    boolean existsByEmailOrAccount_LoginOrPhoneNumber(String email, String login, String phoneNumber);
}
