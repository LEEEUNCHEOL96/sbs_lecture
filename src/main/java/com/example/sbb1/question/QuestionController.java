package com.example.sbb1.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    @GetMapping("/question/detail/{id}")
    public String getQuestion (Model model, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }

}
