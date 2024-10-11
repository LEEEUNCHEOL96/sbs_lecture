package com.example.sbb1.report;

import com.example.sbb1.answer.Answer;
import com.example.sbb1.question.Question;
import com.example.sbb1.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private SiteUser reporter;  // 신고한 사용자

    @ManyToOne
    private Answer answer;  // 신고한 답변

    @ManyToOne
    private Question question;  // 신고한 질문

    @Column(columnDefinition = "TEXT")
    private String reason;  // 신고 사유

    private LocalDateTime reportDate;  // 신고 일시
}
