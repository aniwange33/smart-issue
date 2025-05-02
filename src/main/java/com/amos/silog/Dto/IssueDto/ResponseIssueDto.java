package com.amos.silog.Dto.IssueDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseIssueDto {
    private Long id;
    private String title;
    private String description;
    private String  status;
    private String  severityLevel;
    private String assignedTo;
    private String project;
    private String logUrl;
}
