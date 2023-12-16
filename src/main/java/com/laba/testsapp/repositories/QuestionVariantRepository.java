package com.laba.testsapp.repositories;

import com.laba.testsapp.models.QuestionVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionVariantRepository extends JpaRepository<QuestionVariant, Long> {
}
