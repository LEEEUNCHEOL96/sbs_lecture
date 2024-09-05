package com.example.sbb1.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject); // testJpa4
    Question findBySubjectAndContent(String subject, String content); //testJpa5
    List<Question> findBySubjectLike(String subject); // testJpa6
}
