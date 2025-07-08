package com.amos.silog.issue.mapper;

import com.amos.silog.issue.dto.IssueRequestDto;
import com.amos.silog.issue.dto.IssueResponseDto;
import com.amos.silog.issue.dto.IssueSeverityLevel;
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
                .severityLevel(IssueSeverityLevel.valueOf(key.getSeverityLevel()))
                .build();
    }
}
