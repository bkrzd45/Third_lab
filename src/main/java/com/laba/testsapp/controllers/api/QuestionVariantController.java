package com.laba.testsapp.controllers.api;

import com.laba.testsapp.models.QuestionVariant;
import com.laba.testsapp.repositories.QuestionVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions/variants")
public class QuestionVariantController {
    @Autowired
    private QuestionVariantRepository questionVariantRepository;

    @GetMapping
    public List<QuestionVariant> getAllQuestionVariants() {
        return questionVariantRepository.findAll();
    }

    @GetMapping("/{id}")
    public QuestionVariant getQuestionVariantById(@PathVariable Long id) {
        return questionVariantRepository.findById(id).orElse(null);
    }

    @PostMapping
    public QuestionVariant createQuestionVariant(@RequestBody QuestionVariant questionVariant) {
        return questionVariantRepository.save(questionVariant);
    }

    @PutMapping("/{id}")
    public QuestionVariant updateQuestionVariant(@PathVariable Long id, @RequestBody QuestionVariant questionVariant) {
        questionVariant.setId(id);
        return questionVariantRepository.save(questionVariant);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestionVariant(@PathVariable Long id) {
        questionVariantRepository.deleteById(id);
    }
}
