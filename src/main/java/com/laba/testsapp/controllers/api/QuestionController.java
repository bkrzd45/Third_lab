package com.laba.testsapp.controllers.api;

import com.laba.testsapp.models.Question;
import com.laba.testsapp.models.Test;
import com.laba.testsapp.repositories.QuestionRepository;
import com.laba.testsapp.repositories.QuestionVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionVariantRepository questionVariantRepository;

    @PostMapping("/list")
    public ResponseEntity<ArrayList<Question>> getAllQuestions(@RequestBody Test test) {
        return ResponseEntity.status(HttpStatus.OK).body(questionRepository.findAllByBoundTest(test));
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        if (question.getQuestionVariants() != null) {
            questionVariantRepository.saveAll(question.getQuestionVariants());
        }

        return questionRepository.save(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        question.setId(id);
        return questionRepository.save(question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }
}
