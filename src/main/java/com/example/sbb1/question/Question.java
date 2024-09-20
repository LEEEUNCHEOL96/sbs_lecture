package com.example.sbb1.question;


import com.example.sbb1.answer.Answer;
import com.example.sbb1.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id // 질문의 고유 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Integer id;

    @Column(length = 200)  // 질문의 제목 길이는 200
    private String subject;

    @Column(columnDefinition = "TEXT") // 질문의 내용은 TEXT 형태의 길이제한 없음
    private String content;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime createDate; // 질문의 작성 일시
    private LocalDateTime modifyDate; // 질문의 수정 일시

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // JPA 특성 : 질문 => 자식 질문
    private List<Answer> answerList;

    @ManyToMany // 좋아요 기능
    Set<SiteUser> voter;
}
