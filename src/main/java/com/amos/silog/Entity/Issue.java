package com.amos.silog.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing an issue in the system.
 * This class extends BaseEntity to inherit common fields like id, createdAt, updatedAt, etc.
 */
@Entity
@Table(name = "issues")
@Getter
@Setter
public class Issue extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "severity_level")
    private String severityLevel;

    @Column(name = "assigned_to")
    private String assignedTo;

    // Default constructor
    public Issue() {
    }

    // Constructor with required fields
    public Issue(String title, String status) {
        this.title = title;
        this.status = status;
    }

}