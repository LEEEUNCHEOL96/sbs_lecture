package com.example.sbb1.answer;

import com.example.sbb1.DataNotFoundException;
import com.example.sbb1.question.Question;
import com.example.sbb1.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    // 답변 기능
    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
    // 답변수정 찾기 기능
    public Answer getAnswer(Integer id){
        Optional<Answer> optionalAnswer = this.answerRepository.findById(id);
        if(optionalAnswer.isPresent()){
            return optionalAnswer.get();
        }
        else {
            throw new DataNotFoundException("answer not found");
        }
    }

    // 답변수정 저장 기능
    public void modify(Answer answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    // 답변삭제 기능
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }
}
