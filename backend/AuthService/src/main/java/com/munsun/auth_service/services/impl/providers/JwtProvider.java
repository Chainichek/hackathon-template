package com.munsun.auth_service.services.impl.providers;

import com.auth0.jwt.algorithms.Algorithm;
import com.munsun.auth_service.models.User;

public interface JwtProvider {
    String generate(User user);
    Algorithm getAlgorithm();
}
