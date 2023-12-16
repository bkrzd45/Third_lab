package com.laba.testsapp.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    PUBLISHER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
