package com.amos.silog.mapper;

import com.amos.silog.Dto.IssueDto.IssueRequestDto;
import com.amos.silog.Dto.IssueDto.IssueStatus;
import com.amos.silog.Entity.Issue;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
public class IssueRequestConverter implements   Function<IssueRequestDto, Issue> {

    @Override
    public Issue apply(IssueRequestDto key) {
            Issue issue = new Issue(key.getTitle(), IssueStatus.OPEN.name());
            issue.setDescription(key.getDescription());
            issue.setSeverityLevel(key.getSeverityLevel().name());
            issue.setAssignedTo(key.getAssignedTo());
            issue.setProject( key.getProject());
            return issue;
        }
}
