package ru.babim.lib.jwtprovider.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.crypto.SecretKey;
import java.util.ArrayList;

public class JwtEmptyProvider implements JwtProvider {
    private final JwtProvider provider;

    public JwtEmptyProvider(SecretKey secretKey) {
        this.provider = new JwtClaimsProvider(secretKey, (claims -> new UsernamePasswordAuthenticationToken(null, null, new ArrayList<>())));
    }

    @Override
    public AbstractAuthenticationToken getAuthentication(String token) {
        return this.provider.getAuthentication(token);
    }
}
