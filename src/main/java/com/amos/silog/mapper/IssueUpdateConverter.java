package com.amos.silog.mapper;

import com.amos.silog.Dto.IssueDto.IssueRequestDto;
import com.amos.silog.Dto.IssueDto.IssueStatus;
import com.amos.silog.Entity.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueUpdateConverter implements UpdateEntity<Issue, IssueRequestDto> {
    @Override
    public void updateEntity(Issue entity, IssueRequestDto key) {
        entity.setTitle(key.getTitle());
        entity.setStatus(IssueStatus.OPEN.name());
        entity.setDescription(key.getDescription());
        entity.setSeverityLevel(key.getSeverityLevel().name());
        entity.setAssignedTo(key.getAssignedTo());
        entity.setProject(key.getProject());
    }


}
