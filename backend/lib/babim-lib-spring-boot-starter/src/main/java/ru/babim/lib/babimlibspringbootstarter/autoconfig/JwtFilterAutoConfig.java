package ru.babim.lib.babimlibspringbootstarter.autoconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.babim.lib.babimlibspringbootstarter.properties.JwtFilterProperties;
import ru.babim.lib.jwtprovider.filter.JwtFilter;
import ru.babim.lib.jwtprovider.provider.JwtEmptyProvider;
import ru.babim.lib.jwtprovider.provider.JwtProvider;
import ru.babim.lib.jwtprovider.util.DefaultErrorSender;
import ru.babim.lib.jwtprovider.util.ErrorSender;

import javax.crypto.SecretKey;

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnClass(JwtFilter.class)
@EnableConfigurationProperties(JwtFilterProperties.class)
public class JwtFilterAutoConfig {
    private final JwtFilterProperties jwtFilterProperties;

    @Bean
    @ConditionalOnProperty(prefix = "babim-lib.jwt-filter.secret-key", value = "autoconfigure-bean", havingValue = "true")
    public SecretKey jwsSecretKey() {
        final byte[] secretBytes = jwtFilterProperties.getSecretKey().getUsingBase64()
                ? Decoders.BASE64.decode(jwtFilterProperties.getSecretKey().getKey())
                : jwtFilterProperties.getSecretKey().getKey().getBytes();
        return Keys.hmacShaKeyFor(secretBytes);
    }

    @Bean
    @ConditionalOnMissingBean(JwtProvider.class)
    @ConditionalOnBean(SecretKey.class)
    @ConditionalOnProperty(prefix = "babim-lib.jwt-filter", value = "enabled", havingValue = "true")
    public JwtProvider jwtEmptyProvider(SecretKey jwsSecretKey) {
        return new JwtEmptyProvider(jwsSecretKey);
    }

    @Bean
    @ConditionalOnProperty(prefix = "babim-lib.jwt-filter", value = "enabled", havingValue = "true")
    public ErrorSender defaultErrorSender(ObjectMapper mapper) {
        return new DefaultErrorSender(mapper);
    }
}