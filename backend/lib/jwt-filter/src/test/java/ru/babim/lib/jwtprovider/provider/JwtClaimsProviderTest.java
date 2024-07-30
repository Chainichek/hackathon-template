package ru.babim.lib.jwtprovider.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

class JwtClaimsProviderTest {
    @Test
    void extractJwt() {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String jws = Jwts.builder()
                .subject("User")
                .claim("role", "admin")
                .signWith(key)
                .compact();
        System.out.println(jws);

        Claims claims = Jwts.parser()
//                .verifyWith(key)
                .build()
                .parseSignedClaims(jws)
                .getPayload();
        claims.getSubject();
    }
}