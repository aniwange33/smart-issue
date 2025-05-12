package com.amos.silog.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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