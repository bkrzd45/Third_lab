package com.laba.testsapp.services;

import com.laba.testsapp.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AuthService {
    public void authUserByEntity (User user) {
        Collection<GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            user.getUserName(),
            user.getPassword(),
            roles
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
