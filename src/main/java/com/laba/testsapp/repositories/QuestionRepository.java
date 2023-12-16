package com.laba.testsapp.repositories;

import com.laba.testsapp.models.Question;
import com.laba.testsapp.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public ArrayList<Question> findAllByBoundTest (Test test);
}
