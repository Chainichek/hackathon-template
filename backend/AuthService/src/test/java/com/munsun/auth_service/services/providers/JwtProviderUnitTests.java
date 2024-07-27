package com.munsun.auth_service.services.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.services.impl.providers.JwtProvider;
import com.munsun.auth_service.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class JwtProviderUnitTests {
    @Autowired
    private JwtProvider provider;

    @DisplayName("Test generate correct access token")
    @Test
    public void givenPersistentUser_whenCallGenerateToken_thenReturnCorrectJwtToken() {
        Account persistentAccount = TestUtils.getAccountPersistent_Munir();

        String actualToken = provider.generate(persistentAccount, new Date());

        assertThat(actualToken)
                .isNotNull()
                .isNotBlank();
        JWTVerifier verifier = JWT.require(provider.getAlgorithm())
                .build();
        assertDoesNotThrow(()-> verifier.verify(actualToken));
    }
}
