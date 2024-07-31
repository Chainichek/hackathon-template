package ru.babim.lib.jwtprovider.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import ru.babim.lib.jwtprovider.auuthentication.EmptyAuthenticationToken;

import javax.crypto.SecretKey;

public class JwtEmptyProvider implements JwtProvider {
    private final JwtProvider provider;

    public JwtEmptyProvider(SecretKey secretKey) {
        this.provider = new JwtClaimsProvider(secretKey, (claims -> new EmptyAuthenticationToken(AuthorityUtils.NO_AUTHORITIES)));
    }

    @Override
    public AbstractAuthenticationToken getAuthentication(String token) {
        return this.provider.getAuthentication(token);
    }
}
