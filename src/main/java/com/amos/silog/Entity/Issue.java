package com.amos.silog.Entity;

import com.amos.silog.Dto.IssueDto.IssueResponseDto;
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







}