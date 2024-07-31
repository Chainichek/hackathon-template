package ru.babim.lib.babimlibspringbootstarter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("babim-lib.jwt-filter")
public class JwtFilterProperties {
    @Getter
    @Setter
    public static final class SecretKey {
        private String key;
        private Boolean usingBase64 = false;
        private Boolean autoconfigureBean = true;
    }

    private Boolean enabled;
    private SecretKey secretKey = new SecretKey();
}
