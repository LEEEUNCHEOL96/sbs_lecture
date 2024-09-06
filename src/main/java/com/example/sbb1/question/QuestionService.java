package com.example.sbb1.question;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id){
        Optional<Question> oq = this.questionRepository.findById(id);
        if(oq.isEmpty()){
            return null ;
        }else {
            return oq.get();
        }
    }
}
