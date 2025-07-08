package com.amos.silog.issue.mapper;

import com.amos.silog.auth.model.AppUser;
import com.amos.silog.auth.service.AuthService;
import com.amos.silog.issue.dto.IssueRequestDto;
import com.amos.silog.issue.dto.IssueStatus;
import com.amos.silog.issue.model.Issue;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssueRequestConverter implements Function<IssueRequestDto, Issue> {

    private final AuthService authService;
    @Override
    public Issue apply(IssueRequestDto key) {
        AppUser currentUser = authService.getCurrentUser();
        return new Issue(key.getTitle(), IssueStatus.OPEN.name())
                .withDescription(key.getDescription())
                .withSeverityLevel(key.getSeverityLevel().name())
                .withUser(currentUser)
                .withProject(key.getProject());
    }
}
