package com.amos.silog.issues.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssueConverters {
    public final IssueRequestConverter request;
    public final IssueResponseConverter response;
    public final IssueResponseToRequestConverter toRequest;
    public final IssueUpdateConverter update;

    @Autowired
    public IssueConverters(IssueRequestConverter request,
                           IssueResponseConverter response,
                           IssueResponseToRequestConverter toRequest,
                           IssueUpdateConverter update) {
        this.request = request;
        this.response = response;
        this.toRequest = toRequest;
        this.update = update;
    }
}