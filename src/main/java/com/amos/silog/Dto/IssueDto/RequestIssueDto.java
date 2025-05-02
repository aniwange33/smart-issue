package com.amos.silog.Dto.IssueDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestIssueDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @JsonIgnore
    private IssueStatus status;
    @NotNull
    private IssueSeverityLevel severityLevel;
    private String assignedTo;
    @NotNull
    private String project;
    @JsonIgnore
    private String logUrl;
}
