package com.amos.silog.resolution.mapper;

import com.amos.silog.auth.model.AppUser;
import com.amos.silog.auth.service.AuthService;
import com.amos.silog.issue.model.Issue;
import com.amos.silog.resolution.dto.ResolutionRequestDto;
import com.amos.silog.resolution.model.Resolution;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Converter for transforming {@link ResolutionRequestDto} objects to {@link Resolution} entities.
 */
@Component
@RequiredArgsConstructor
public class ResolutionRequestConverter implements Function<ResolutionRequestDto, Resolution> {

    private final AuthService authService;

    @Override
    public Resolution apply(ResolutionRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Resolution resolution = new Resolution();
        // The issue and resolvedBy will be set by the service
        resolution.withResolutionSummary(dto.getResolutionSummary())
                .withTimeSpentHours(dto.getTimeSpentHours())
                .withResolvedAt(dto.getResolvedAt() != null ? dto.getResolvedAt() : LocalDateTime.now());
        return resolution;
    }

    /**
     * Updates an existing Resolution entity with data from a ResolutionRequestDto
     * 
     * @param resolution the existing Resolution entity
     * @param dto the DTO with updated data
     * @return the updated Resolution entity
     */
    public Resolution update(Resolution resolution, ResolutionRequestDto dto) {
        if (resolution == null || dto == null) {
            return resolution;
        }
        resolution.withResolutionSummary(dto.getResolutionSummary())
                .withTimeSpentHours(dto.getTimeSpentHours());
        if (dto.getResolvedAt() != null) {
            resolution.withResolvedAt(dto.getResolvedAt());
        }
        return resolution;
    }

    /**
     * Sets the issue and resolvedBy fields of a Resolution entity
     * 
     * @param resolution the Resolution entity
     * @param issue the Issue entity
     * @return the updated Resolution entity
     */
    public Resolution withIssue(Resolution resolution, Issue issue) {
        if (resolution == null) {
            return null;
        }
        AppUser resolvedBy = authService.getCurrentUser();
        return resolution.withIssue(issue).withResolvedBy(resolvedBy);
    }
}