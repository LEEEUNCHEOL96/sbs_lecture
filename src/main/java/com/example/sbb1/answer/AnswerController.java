package com.example.sbb1.answer;

import com.example.sbb1.question.Question;
import com.example.sbb1.question.QuestionService;
import com.example.sbb1.user.SiteUser;
import com.example.sbb1.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    // 답변 생성 컨트롤러
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {

        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        Answer answer = this.answerService.create(question, answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }

    // 답변 수정 컨트롤러 겟맵핑
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);

        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "answer_form";
    }

    // 답변 수정 컨트롤러 포스트맵핑
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                               @PathVariable("id") Integer id, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "answer_form";
        }

        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }

    // 답변 삭제 컨트롤러
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);

        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

/*    // 답변 추천 컨트롤러
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerVote(@PathVariable("id") Integer id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        this.answerService.vote(answer, siteUser);

        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }*/

    // ajax를 통한 답변 추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    @ResponseBody
    public String answerVote(@PathVariable("id") Integer id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.answerService.vote(answer, siteUser);

        Answer votedAnswer = this.answerService.getAnswer(id);
        Integer count = votedAnswer.getVoter().size();

        return count.toString();
    }
}
