package ru.babim.lib.jwtprovider.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.babim.lib.jwtprovider.exception.UnauthorizedException;
import ru.babim.lib.jwtprovider.resolver.JwtResolver;
import ru.babim.lib.jwtprovider.util.ErrorSender;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTH_HEADER_NAME = "Authorization";

    private final ErrorSender errorSender;
    private final JwtResolver jwtResolver;

    private final List<String> requestMatchers;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authToken;
        try {
            authToken = resolveToken(request);
            logger.info("Incoming request on secured uri: method = %s,  requestURI = %s, authToken = %s".formatted(request.getMethod(),
                    request.getRequestURI(),
                    authToken)
            );
        } catch (UnauthorizedException e) {
            logger.warn("Unauthorized request to %s: Missing %s header".formatted(AUTH_HEADER_NAME, request.getRequestURI()));
            errorSender.sendUnauthorized(e, response);
            return;
        }
        try {
            jwtResolver.resolve(authToken);
        } catch (BadCredentialsException e) {
            logger.warn("Forbidden request to %s: %s".formatted(request.getRequestURI(), e.getMessage()));
            errorSender.sendBadCredentials(e, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return requestMatchers.stream()
                .noneMatch(uriPattern -> Pattern.matches(uriPattern.replace("**", ".*"), request.getRequestURI()));
    }

    private String resolveToken(final HttpServletRequest request) throws UnauthorizedException {
        final String bearer = request.getHeader(AUTH_HEADER_NAME);
        if (!StringUtils.hasText(bearer) || !bearer.startsWith("Bearer ")) {
            throw new UnauthorizedException("Unauthorized request on secured URI");
        }
        return bearer.substring(7);
    }
}
