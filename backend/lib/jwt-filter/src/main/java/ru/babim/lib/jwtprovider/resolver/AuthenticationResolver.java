package ru.babim.lib.jwtprovider.resolver;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.babim.lib.jwtprovider.provider.JwtProvider;

@RequiredArgsConstructor
public class AuthenticationResolver implements JwtResolver {

    private final JwtProvider jwtProvider;

    @Override
    public void resolve(String authToken) {
        final @NonNull Authentication authentication = jwtProvider.getAuthentication(authToken);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
