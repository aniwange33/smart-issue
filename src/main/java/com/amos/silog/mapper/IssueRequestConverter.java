package com.amos.silog.mapper;

import com.amos.silog.Dto.IssueDto.IssueRequestDto;
import com.amos.silog.Dto.IssueDto.IssueStatus;
import com.amos.silog.Entity.Issue;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
public class IssueRequestConverter implements Function<IssueRequestDto, Issue> {

    @Override
    public Issue apply(IssueRequestDto key) {
        return new Issue(key.getTitle(), IssueStatus.OPEN.name())
                .withDescription(key.getDescription())
                .withSeverityLevel(key.getSeverityLevel().name())
                .withAssignedTo(key.getAssignedTo())
                .withProject(key.getProject());
    }
}
