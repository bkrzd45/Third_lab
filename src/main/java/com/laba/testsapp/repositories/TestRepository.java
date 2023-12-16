package com.laba.testsapp.repositories;

import com.laba.testsapp.models.Test;
import com.laba.testsapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    public List<Test> findAllByOwner (User owner);
}
