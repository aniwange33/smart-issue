package com.amos.silog.issue.model;

import com.amos.silog.auth.model.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


/**
 * Entity representing an issue in the system.
 * This class extends BaseEntity to inherit common fields like id, createdAt, updatedAt, etc.
 */
@Entity
@Table(name = "issues")
@Getter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
        region = "issueCache")
@SQLDelete(sql = "UPDATE issues SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class Issue extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, name = "severity_level")
    private String severityLevel;

    @Column(name = "assigned_to")
    private String assignedTo;

    private String project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_by", nullable = false, referencedColumnName = "id")
    private AppUser  logBy;

    @Column(name = "log_url")
    private String logUrl;

    public Issue(String title, String status) {
        this.title = title;
        this.status = status;
    }



    public Issue withTitle(String title) {
        this.title = title;
        return this;
    }

    public Issue withUser(AppUser logBy) {
        this.logBy = logBy;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    public Issue withSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
        return this;
    }

    public Issue withAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public Issue withProject(String project) {
        this.project = project;
        return this;
    }

    public Issue withLogUrl(String logUrl) {
        this.logUrl = logUrl;
        return this;
    }
}