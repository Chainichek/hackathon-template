package com.munsun.auth_service.mapping;

import com.munsun.auth_service.dto.request.AccountDto;
import com.munsun.auth_service.dto.response.AccountPersistentDto;
import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountMapperUnitTests {
    @Autowired
    private AccountMapper accountMapper;

    @DisplayName("Test map AccountDto to Account")
    @Test
    public void givenAccountDto_whenMapToAccount_thenReturnValidAccount() {
        AccountDto accountDto = TestUtils.getAccountDto_Munir();
        Account expectedAccount = TestUtils.getAccountTransient_Munir();

        Account actualAccount = accountMapper.map(accountDto);

        assertThat(actualAccount)
                .usingRecursiveComparison()
                .ignoringFields("accountId", "role")
                .isEqualTo(expectedAccount);
    }

    @DisplayName("Test map Account to AccountPersistentDto")
    @Test
    public void givenAccountEntityPersistent_whenMapToAccountPersistentDto_thenReturnValidAccountPersistentDto() {
        Account account = TestUtils.getAccountPersistent_Munir();
        AccountPersistentDto expectedAccountPersistentDto = TestUtils.getAccountPersistentDto_Munir(account.getAccountId());

        AccountPersistentDto actualAccount = accountMapper.map(account);

        assertThat(actualAccount)
                .usingRecursiveComparison()
                .isEqualTo(expectedAccountPersistentDto);
    }
}