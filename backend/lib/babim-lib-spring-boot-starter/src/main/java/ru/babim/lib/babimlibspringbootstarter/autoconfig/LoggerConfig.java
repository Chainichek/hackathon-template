package ru.babim.lib.babimlibspringbootstarter.autoconfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.babim.lib.babimlibspringbootstarter.properties.LoggerProperties;
import ru.babim.lib.logger.aspect.LoggableAspect;

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnClass(LoggableAspect.class)
@EnableConfigurationProperties(LoggerProperties.class)
public class LoggerConfig {
    private final LoggerProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "babim-lib.logger", value = "enabled", havingValue = "true")
    public LoggableAspect loggableAspect() {
        final LoggableAspect loggableAspect = new LoggableAspect();
        log.info("Babim-lib successfully initialized babim-lib.logger");
        return loggableAspect;
    }
}
