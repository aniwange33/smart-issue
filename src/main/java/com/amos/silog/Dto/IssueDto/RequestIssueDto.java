package com.amos.silog.Dto.IssueDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestIssueDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private IssueSeverityLevel severityLevel;
    private String assignedTo;
    @NotNull
    private String project;
}
