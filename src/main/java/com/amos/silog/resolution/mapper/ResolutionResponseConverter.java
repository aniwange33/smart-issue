package com.amos.silog.resolution.mapper;

import com.amos.silog.auth.dto.UserDto;
import com.amos.silog.issue.mapper.IssueResponseConverter;
import com.amos.silog.resolution.dto.ResolutionResponseDto;
import com.amos.silog.resolution.model.Resolution;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

/**
 * Converter for transforming {@link Resolution} entities to {@link ResolutionResponseDto} objects.
 */
@Component
public class ResolutionResponseConverter implements Function<Resolution, ResolutionResponseDto> {

    private final IssueResponseConverter issueResponseConverter;

    public ResolutionResponseConverter(IssueResponseConverter issueResponseConverter) {
        this.issueResponseConverter = issueResponseConverter;
    }

    @Override
    public ResolutionResponseDto apply(Resolution resolution) {
        if (resolution == null) {
            return null;
        }

        return ResolutionResponseDto.builder()
                .id(resolution.getId())
                .issue(issueResponseConverter.apply(resolution.getIssue()))
                .resolvedBy(convertToUserDto(resolution))
                .resolutionSummary(resolution.getResolutionSummary())
                .aiEnhancedSummary(resolution.getAiEnhancedSummary())
                .timeSpentHours(resolution.getTimeSpentHours())
                .resolvedAt(resolution.getResolvedAt())
                .createdAt(resolution.getCreatedAt())
                .updatedAt(resolution.getUpdatedAt())
                .build();
    }

    private UserDto convertToUserDto(Resolution resolution) {
        if (resolution.getResolvedBy() == null) {
            return null;
        }
        
        return UserDto.builder()
                .id(resolution.getResolvedBy().getId())
                .name(resolution.getResolvedBy().getName())
                .email(resolution.getResolvedBy().getEmail())
                .role(resolution.getResolvedBy().getRole())
                .build();
    }
}