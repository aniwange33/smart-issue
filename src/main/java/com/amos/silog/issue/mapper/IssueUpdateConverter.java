package com.amos.silog.issue.mapper;

import com.amos.silog.issue.dto.IssueRequestDto;
import com.amos.silog.issue.model.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueUpdateConverter implements UpdateEntity<Issue, IssueRequestDto> {
    @Override
    public void updateEntity(Issue entity, IssueRequestDto key) {
        entity.withTitle(key.getTitle())
                .withDescription(key.getDescription())
                .withSeverityLevel(key.getSeverityLevel().name())
                .withProject(key.getProject());
    }


}
