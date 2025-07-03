package com.amos.silog.issues.mapper;

import com.amos.silog.issues.dto.IssueResponseDto;
import com.amos.silog.issues.model.Issue;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
public class IssueResponseConverter implements Function<Issue, IssueResponseDto> {

    @Override
    public IssueResponseDto apply(Issue key) {
        return IssueResponseDto.builder()
                .id(key.getId())
                .title(key.getTitle())
                .description(key.getDescription())
                .assignedTo(key.getAssignedTo())
                .severityLevel(key.getSeverityLevel())
                .logUrl(key.getLogUrl())
                .status(key.getStatus())
                .project(key.getProject())
                .uuid(key.getUuid())
                .build();
    }
}
