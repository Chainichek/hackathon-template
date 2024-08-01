package com.munsun.auth_service.repositories;

import com.munsun.auth_service.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountByLogin(String login);
    boolean existsByLogin(String login);
}
