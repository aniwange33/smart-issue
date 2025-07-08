package com.amos.silog.resolution.dto;

import com.amos.silog.auth.dto.UserDto;
import com.amos.silog.issue.dto.IssueResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for returning resolution data to clients
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolutionResponseDto {

    /**
     * Resolution ID
     */
    private Long id;

    /**
     * Issue that was resolved
     */
    private IssueResponseDto issue;

    /**
     * User who resolved the issue
     */
    private UserDto resolvedBy;

    /**
     * Summary of the resolution
     */
    private String resolutionSummary;

    /**
     * AI-enhanced summary of the resolution
     */
    private String aiEnhancedSummary;

    /**
     * Time spent resolving the issue in hours
     */
    private BigDecimal timeSpentHours;

    /**
     * When the issue was resolved
     */
    private LocalDateTime resolvedAt;

    /**
     * When the resolution was created
     */
    private LocalDateTime createdAt;

    /**
     * When the resolution was last updated
     */
    private LocalDateTime updatedAt;
}