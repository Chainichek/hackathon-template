package ru.babim.template.activity.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    EXPERT,
    RECRUITER,
    CANDIDATE;

    @Override
    public String getAuthority() {
        return "ROLE_%s".formatted(this.name());
    }
}
