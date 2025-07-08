package com.amos.silog.issue.dto;

import lombok.Data;

@Data
public class IssueFilterRequestDto {
    private IssueStatus status;
    private IssueSeverityLevel severityLevel;
    private String project;
}