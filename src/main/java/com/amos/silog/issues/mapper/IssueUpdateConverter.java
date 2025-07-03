package com.amos.silog.issues.mapper;

import com.amos.silog.issues.dto.IssueRequestDto;
import com.amos.silog.issues.model.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueUpdateConverter implements UpdateEntity<Issue, IssueRequestDto> {
    @Override
    public void updateEntity(Issue entity, IssueRequestDto key) {
        entity.withTitle(key.getTitle())
                .withDescription(key.getDescription())
                .withSeverityLevel(key.getSeverityLevel().name())
                .withAssignedTo(key.getAssignedTo())
                .withProject(key.getProject());
    }


}
