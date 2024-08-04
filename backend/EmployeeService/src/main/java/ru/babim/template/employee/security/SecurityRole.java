package ru.babim.template.employee.security;

import org.springframework.security.core.GrantedAuthority;

public enum SecurityRole implements GrantedAuthority {
    ADMIN,
    EXPERT,
    RECRUITER,
    CANDIDATE;

    @Override
    public String getAuthority() {
        return "ROLE_%s".formatted(this.name());
    }
}