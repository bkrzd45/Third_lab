package com.laba.testsapp.controllers.frontend;

import com.laba.testsapp.models.Test;
import com.laba.testsapp.models.User;
import com.laba.testsapp.repositories.TestRepository;
import com.laba.testsapp.responses.error.UserAlreadyExistsException;
import com.laba.testsapp.services.AuthService;
import com.laba.testsapp.services.UserDetailsService;
import com.laba.testsapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TestRepository testRepository;

    private static final Map<Integer, String> errorDescriptions = new HashMap<>();

    static {
        errorDescriptions.put(1, "Fill all fields");
        errorDescriptions.put(2, "Passwords didn't match");
        errorDescriptions.put(3, "User already exists");
        errorDescriptions.put(4, "Incorrect login or password");
    }

    @GetMapping("/")
    public ModelAndView mainPage () {
        ModelAndView modelAndView = new ModelAndView("index");

        User currentUser = this.userDetailsService.getCurrentUser();

        modelAndView.addObject("userName", currentUser.getUserName());
        modelAndView.addObject("userId", currentUser.getId());

        List<Test> testList = testRepository.findAllByOwner(currentUser);

        modelAndView.addObject("tests", testList);

        return modelAndView;
    }

    @GetMapping("/auth")
    public ModelAndView authPage (@RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("auth");

        if (error != null) {
            modelAndView.addObject("error", errorDescriptions.getOrDefault(4, error));
        }

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerPageForm (
        @RequestParam(value = "error", required = false) Integer error
    ) {
        ModelAndView modelAndView = new ModelAndView("register");

        String errorDescription = errorDescriptions.getOrDefault(error, "");
        modelAndView.addObject("error", errorDescription);

        return modelAndView;
    }

    @PostMapping("/register")
    public String registerPage (
        @RequestParam(value = "username", required = false) String userName,
        @RequestParam(value = "password", required = false) String password,
        @RequestParam(value = "repeat_password", required = false) String repeatPassword
    ) {
        if (!(userName != null && password != null && repeatPassword != null)) {
            return "redirect:/register?error=1";
        }

        if (!password.contentEquals(repeatPassword)) {
            return "redirect:/register?error=2";
        }

        try {
            User user = userService.registerUser(userName, password);

            authService.authUserByEntity(user);

            return "redirect:/";
        } catch (UserAlreadyExistsException e) {
            return "redirect:/register?error=3";
        }
    }
}