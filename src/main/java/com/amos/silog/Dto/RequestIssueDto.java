package com.amos.silog.Dto;

import lombok.Data;

@Data
public class RequestIssueDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assignee;
    private String reporter;
    private String type;
    private String resolution;
    private String project;
}
