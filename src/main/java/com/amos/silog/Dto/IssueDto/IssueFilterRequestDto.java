package com.amos.silog.Dto.IssueDto;

import lombok.Data;

@Data
public class IssueFilterRequestDto {
    private IssueStatus status;
    private IssueSeverityLevel severityLevel;
    private String project;
}