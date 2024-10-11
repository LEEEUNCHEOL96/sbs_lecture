package com.example.sbb1.report;

import com.example.sbb1.answer.Answer;
import com.example.sbb1.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public Report reportAnswer(SiteUser reporter, Answer answer, String reason) {
        Report report = new Report();
        report.setReporter(reporter);
        report.setAnswer(answer);
        report.setReason(reason);
        report.setReportDate(LocalDateTime.now());
        return reportRepository.save(report);
    }

   /* @Transactional
    public Report reportQuestion(SiteUser reporter, Question question, String reason) {
        Report report = new Report();
        report.setReporter(reporter);
        report.setQuestion(question);
        report.setReason(reason);
        report.setReportDate(LocalDateTime.now());
        return reportRepository.save(report);
    }*/
}