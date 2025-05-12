package com.amos.silog.mapper;

import com.amos.silog.Dto.IssueDto.IssueRequestDto;
import com.amos.silog.Entity.Issue;
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
