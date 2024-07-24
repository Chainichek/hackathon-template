package com.munsun.auth_service.mapping;

import com.munsun.auth_service.dto.request.UserInfoDto;
import com.munsun.auth_service.models.User;
import com.munsun.auth_service.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMapperUnitTests {
    @Autowired
    private UserMapper mapper;

    @DisplayName("Test map userInfoDto to user model")
    @Test
    public void givenUserInfoDto_whenMapToUserModel_thenReturnCorrectUser() {
        UserInfoDto munitUserInfo = TestUtils.getUserInfoDto_Munir();
        User expectedUser = TestUtils.getUser_Munir();

        User actualUser = mapper.map(munitUserInfo);

        assertThat(actualUser)
                .usingRecursiveComparison()
                .ignoringFields("userId", "accountId")
                .isEqualTo(expectedUser);
    }
}
