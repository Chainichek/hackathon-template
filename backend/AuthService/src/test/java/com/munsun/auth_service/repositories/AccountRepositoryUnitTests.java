package com.munsun.auth_service.repositories;

import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.utils.PostgresContainer;
import com.munsun.auth_service.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountRepositoryUnitTests extends PostgresContainer {
    @Autowired
    private AccountRepository repository;

    @DisplayName("Test success save of user")
    @Test
    public void givenTransientUser_whenSaveUser_thenReturnUpdateIdUser() {
        Account accountTransient = TestUtils.getAccountTransient_Munir();

        repository.save(accountTransient);

        assertThat(accountTransient)
                .extracting(Account::getAccountId)
                .isNotNull();
    }
}
