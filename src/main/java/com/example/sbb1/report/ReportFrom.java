package com.example.sbb1.report;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportFrom {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String reportContent;
}
