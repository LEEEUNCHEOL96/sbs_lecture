package com.example.sbb1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject); // testJpa4
    Question findBySubjectAndContent(String subject, String content); //testJpa5
}
