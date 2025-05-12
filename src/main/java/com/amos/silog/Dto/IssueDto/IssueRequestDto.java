package com.amos.silog.Dto.IssueDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueRequestDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private IssueSeverityLevel severityLevel;
    private String assignedTo;
    private String project;
}
