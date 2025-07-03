package com.amos.silog.issues.mapper;

import com.amos.silog.issues.dto.IssueRequestDto;
import com.amos.silog.issues.dto.IssueStatus;
import com.amos.silog.issues.model.Issue;
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
