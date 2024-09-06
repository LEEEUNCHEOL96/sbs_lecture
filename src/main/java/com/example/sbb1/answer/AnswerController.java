package com.example.sbb1.answer;

import com.example.sbb1.question.Question;
import com.example.sbb1.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createAnswer (Model model, @PathVariable("id") Integer id,
                                @RequestParam("content") String content){
        Question question = this.questionService.getQuestion(id);

        // TODO: 답변등록하기
        return String.format("redirect:/question/detail/%s", id);
    }

}
