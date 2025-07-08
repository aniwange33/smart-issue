package com.amos.silog.resolution.model;

import com.amos.silog.auth.model.AppUser;
import com.amos.silog.issue.model.BaseEntity;
import com.amos.silog.issue.model.Issue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a resolution for an issue in the system.
 * This class extends BaseEntity to inherit common fields like id, createdAt, updatedAt, etc.
 */
@Entity
@Table(name = "resolutions")
@Getter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,
        region = "resolutionCache")
@SQLDelete(sql = "UPDATE resolutions SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class Resolution extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by", nullable = false)
    private AppUser resolvedBy;

    @Column(name = "resolution_summary", nullable = false, columnDefinition = "TEXT")
    private String resolutionSummary;

    @Column(name = "ai_enhanced_summary", columnDefinition = "TEXT")
    private String aiEnhancedSummary;

    @Column(name = "time_spent_hours", nullable = false, precision = 10, scale = 2)
    private BigDecimal timeSpentHours;

    @Column(name = "resolved_at", nullable = false)
    private LocalDateTime resolvedAt;

    @Column(name = "notification_sent", nullable = false)
    private boolean notificationSent = false;

    public Resolution withIssue(Issue issue) {
        this.issue = issue;
        return this;
    }

    public Resolution withResolvedBy(AppUser resolvedBy) {
        this.resolvedBy = resolvedBy;
        return this;
    }

    public Resolution withResolutionSummary(String resolutionSummary) {
        this.resolutionSummary = resolutionSummary;
        return this;
    }

    public Resolution withAiEnhancedSummary(String aiEnhancedSummary) {
        this.aiEnhancedSummary = aiEnhancedSummary;
        return this;
    }

    public Resolution withTimeSpentHours(BigDecimal timeSpentHours) {
        this.timeSpentHours = timeSpentHours;
        return this;
    }

    public Resolution withResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
        return this;
    }

    public Resolution withNotificationSent(boolean notificationSent) {
        this.notificationSent = notificationSent;
        return this;
    }
}