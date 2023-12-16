package com.laba.testsapp.controllers.api;

import com.laba.testsapp.models.User;
import com.laba.testsapp.repositories.UserRepository;
import com.laba.testsapp.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    protected UserRepository userRepository;

    protected UserDetailsService userDetailsService;

    @Autowired
    public UserController (
        UserRepository userRepository,
        UserDetailsService userDetailsService
    ) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/api/getUser")
    public User getUserInfo (@RequestParam("user_id") Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @GetMapping("/api/getCurrentUser")
    public User getCurrentUser () {
        return userDetailsService.getCurrentUser();
    }
}
