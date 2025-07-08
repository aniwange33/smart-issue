package com.amos.silog.resolution.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for creating or updating a resolution
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResolutionRequestDto {

    /**
     * ID of the issue being resolved
     */
    @NotNull(message = "Issue ID is required")
    private Long issueId;

    /**
     * Summary of the resolution
     */
    @NotBlank(message = "Resolution summary is required")
    @Size(min = 10, max = 5000, message = "Resolution summary must be between 10 and 5000 characters")
    private String resolutionSummary;

    /**
     * Time spent resolving the issue in hours
     */
    @NotNull(message = "Time spent is required")
    @DecimalMin(value = "0.01", message = "Time spent must be greater than 0")
    private BigDecimal timeSpentHours;

    /**
     * When the issue was resolved
     * If not provided, current time will be used
     */
    private LocalDateTime resolvedAt;
}