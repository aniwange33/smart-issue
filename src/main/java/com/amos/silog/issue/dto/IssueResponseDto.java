package com.amos.silog.issue.dto;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class IssueResponseDto  implements Serializable {
    private Long id;
    private String uuid;
    private String title;
    private String description;
    private String status;
    private String severityLevel;
    private String assignedTo;
    private String project;
    private String logBy;
    private String logUrl;

}
