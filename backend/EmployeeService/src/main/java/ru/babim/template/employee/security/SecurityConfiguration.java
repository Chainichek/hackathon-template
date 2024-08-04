package ru.babim.template.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.babim.lib.jwtprovider.filter.JwtFilter;
import ru.babim.lib.jwtprovider.provider.JwtClaimsProvider;
import ru.babim.lib.jwtprovider.provider.JwtProvider;
import ru.babim.lib.jwtprovider.util.ErrorSender;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.List;

@EnableWebMvc
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private static final String[] PRIVATE_URI = {
            "/api/v1/employees",
            "/api/v1/groups"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                            Arrays.stream(PRIVATE_URI)
                                    .forEach(uri -> auth.requestMatchers(uri).authenticated());
                            auth.anyRequest().permitAll();
                        }
                )
                .sessionManagement(sees -> sees.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtProvider jwtClaimsProvider(SecretKey jwsSecretKey) {
        return new JwtClaimsProvider(jwsSecretKey, claims -> new UsernamePasswordAuthenticationToken(
                claims.get("login", String.class),
                null,
                List.of(SecurityRole.valueOf(claims.get("role", String.class)))
        ));
    }

    @Bean
    public JwtFilter jwtFilter(ErrorSender errorSender,
                               JwtProvider jwtProvider) {
        return new JwtFilter(errorSender, jwtProvider, List.of(PRIVATE_URI));
    }
}
