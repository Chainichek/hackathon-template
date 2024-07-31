package ru.babim.lib.jwtprovider.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;

import javax.crypto.SecretKey;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class JwtClaimsProvider implements JwtProvider {
    private final SecretKey key;
    private final Function<Claims, AbstractAuthenticationToken> tokenProvider;

    @Override
    public AbstractAuthenticationToken getAuthentication(String token) {
        if (token == null) {
            throw new BadCredentialsException("Token is null");
        }
        try {
            final Claims claims = extractClaims(token);
            return tokenProvider.apply(claims);
        } catch (JwtException | IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage(), e.getCause());
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
