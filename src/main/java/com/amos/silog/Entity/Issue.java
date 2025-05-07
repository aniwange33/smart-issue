package com.amos.silog.Entity;

import com.amos.silog.Dto.IssueDto.IssueStatus;
import com.amos.silog.Dto.IssueDto.RequestIssueDto;
import com.amos.silog.Dto.IssueDto.ResponseIssueDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * Entity representing an issue in the system.
 * This class extends BaseEntity to inherit common fields like id, createdAt, updatedAt, etc.
 */
@Entity
@Table(name = "issues")
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
        region = "issueCache")
public class Issue extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "severity_level")
    private String severityLevel;

    @Column(name = "assigned_to")
    private String assignedTo;

    private String project;

    @Column(name = "log_url")
    private String logUrl;


    // Default constructor
    public Issue() {
    }

    // Constructor with required fields
    public Issue(String title, String status) {
        this.title = title;
        this.status = status;
    }

    public ResponseIssueDto toResponseDto() {
        return new ResponseIssueDto(this);
    }

    public void applyRequest(RequestIssueDto requestIssueDto) {
        this.title = requestIssueDto.getTitle();
        this.status = IssueStatus.OPEN.name();
        this.description = requestIssueDto.getDescription();
        this.severityLevel = requestIssueDto.getSeverityLevel().name();
        this.assignedTo = requestIssueDto.getAssignedTo();
        this.project = requestIssueDto.getProject();
    }


}