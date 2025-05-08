package com.amos.silog.mapper;

import com.amos.silog.Dto.IssueDto.IssueRequestDto;
import com.amos.silog.Dto.IssueDto.IssueResponseDto;
import com.amos.silog.Dto.IssueDto.IssueSeverityLevel;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
public class IssueResponseToRequestConverter implements Function<IssueResponseDto, IssueRequestDto> {

    @Override
    public IssueRequestDto apply(IssueResponseDto key) {
        return IssueRequestDto.builder()
                .title(key.getTitle())
                .description(key.getDescription())
                .id(key.getId())
                .project(key.getProject())
                .assignedTo(key.getAssignedTo())
                .severityLevel(IssueSeverityLevel.valueOf(key.getSeverityLevel()))
                .build();
    }
}
