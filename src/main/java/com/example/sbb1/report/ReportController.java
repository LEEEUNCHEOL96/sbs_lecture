package com.example.sbb1.report;

import com.example.sbb1.answer.Answer;
import com.example.sbb1.answer.AnswerService;
import com.example.sbb1.question.QuestionService;
import com.example.sbb1.user.SiteUser;
import com.example.sbb1.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    // 답변 신고 처리
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/report-answer/{id}")
    @ResponseBody
    public String reportAnswer(@PathVariable("id") Integer id,
                               @RequestParam("reason") String reason,
                               Principal principal) {

        SiteUser siteUser = userService.getUser(principal.getName());
        Answer answer = answerService.getAnswer(id);  // ID로 답변 찾기

        if (reason.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "신고 사유를 선택해주세요.");
        }

        reportService.reportAnswer(siteUser, answer, reason);  // 신고 처리
        return "신고가 완료되었습니다.";
    }
/*
    // 질문 신고 처리
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/report-question/{id}")
    @ResponseBody
    public String reportQuestion(@PathVariable("id") Integer id,
                                 @RequestParam("reason") String reason,
                                 Principal principal) {

        SiteUser siteUser = userService.getUser(principal.getName());
        Question question = questionService.getQuestion(id);

        // 신고 처리
        if (reason.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "신고 사유를 선택해주세요.");
        }

        reportService.reportQuestion(siteUser, question, reason);
        return "신고가 완료되었습니다.";
    }*/
}