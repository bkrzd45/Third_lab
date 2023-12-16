package com.laba.testsapp.controllers.api;

import com.laba.testsapp.models.Test;
import com.laba.testsapp.repositories.QuestionRepository;
import com.laba.testsapp.repositories.TestRepository;
import com.laba.testsapp.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @GetMapping("/{id}")
    public Test getTestById(@PathVariable Long id) {
        return testRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {
        test.setOwner(userDetailsService.getCurrentUser());

        if (test.getQuestions() != null) {
            questionRepository.saveAll(test.getQuestions());
        }

        return testRepository.save(test);
    }

    @PutMapping("/{id}")
    public Test updateTest(@PathVariable Long id, @RequestBody Test test) {
        test.setId(id);
        test.setOwner(userDetailsService.getCurrentUser());
        return testRepository.save(test);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id) {
        testRepository.deleteById(id);
    }
}
