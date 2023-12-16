package com.laba.testsapp.controllers.frontend;

import com.laba.testsapp.models.Test;
import com.laba.testsapp.repositories.TestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestFrontendController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/edit/{id}")
    public ModelAndView editTest (@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");

        Test test = testRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Test not exists"));

        modelAndView.addObject("test", test);

        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewTest (@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("view");

        Test test = testRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Test not exists"));

        modelAndView.addObject("test", test);

        return modelAndView;
    }
}
