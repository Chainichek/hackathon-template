package ru.chainichek.hackathon.template.activity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ActivityApplicationTests {

    @Test
    void contextLoads() {
    }

}
