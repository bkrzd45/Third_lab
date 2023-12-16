package com.laba.testsapp.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<QuestionVariant> questionVariants;

    @ManyToOne
    private Test boundTest;

    @OneToOne
    private QuestionVariant correctAnswer;

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<QuestionVariant> getQuestionVariants() {
        return questionVariants;
    }

    public void setQuestionVariants(List<QuestionVariant> questionVariants) {
        this.questionVariants = questionVariants;
    }

    public Test getBoundTest() {
        return boundTest;
    }

    public void setBoundTest(Test boundTest) {
        this.boundTest = boundTest;
    }

    public QuestionVariant getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(QuestionVariant correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
