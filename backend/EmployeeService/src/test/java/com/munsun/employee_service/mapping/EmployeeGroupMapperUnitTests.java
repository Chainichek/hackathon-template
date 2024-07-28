package com.munsun.employee_service.mapping;

import com.munsun.employee_service.dto.response.EmployeeGroupDto;
import com.munsun.employee_service.models.EmployeeGroup;
import com.munsun.employee_service.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeGroupMapperUnitTests {
    @Autowired
    private EmployeeGroupMapper mapper;

    @DisplayName("Test map persistent EmployeeGroup to EmployeeGroupDto")
    @Test
    public void givenPersistentEmployeeGroup_whenMapToEmployeeGroupDto_thenReturnValidEmployeeGroupDto() {
        EmployeeGroup persistentEmployeeGroup = TestUtils.getEmployeeGroupPersistent_1();
        EmployeeGroupDto expectedEmployeeGroupDto = TestUtils.getEmployeeGroupDto_1();

        EmployeeGroupDto actualEmployeeInfoDto = mapper.map(persistentEmployeeGroup);

        assertThat(actualEmployeeInfoDto)
                .usingRecursiveComparison()
                .ignoringFields(EmployeeGroup.Fields.isRemoved.name(),
                                EmployeeGroup.Fields.groupId.name())
                .isEqualTo(expectedEmployeeGroupDto);
    }
}
