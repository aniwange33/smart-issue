package com.amos.silog.Dto.IssueDto;

import com.amos.silog.Entity.Issue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
public class ResponseIssueDto {
    private Long id;
    private String title;
    private String description;
    @Setter(AccessLevel.NONE)
    private String status;
    private String severityLevel;
    private String assignedTo;
    private String project;
    private String logUrl;

    public ResponseIssueDto(Issue issue) {
        this.title = issue.getTitle();
        this.description = issue.getDescription();
        this.id = issue.getId();
        this.status = issue.getStatus();
        this.project = issue.getProject();
        this.logUrl = issue.getLogUrl();
        this.assignedTo = issue.getAssignedTo();
        this.severityLevel = issue.getSeverityLevel();
    }

    @JsonIgnore
    public RequestIssueDto getRequestDto() {
        return RequestIssueDto.builder()
                .title(this.getTitle())
                .description(this.getDescription())
                .id(this.getId())
                .project(this.getProject())
                .assignedTo(this.getAssignedTo())
                .severityLevel(IssueSeverityLevel.valueOf(this.getSeverityLevel()))
                .build();
    }


}
