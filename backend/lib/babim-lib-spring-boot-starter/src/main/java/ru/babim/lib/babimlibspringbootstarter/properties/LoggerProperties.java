package ru.babim.lib.babimlibspringbootstarter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("babim-lib.logger")
public class LoggerProperties {
    private Boolean enabled;
}
