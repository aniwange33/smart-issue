package com.amos.silog.issue.service;

import com.amos.silog.issue.dto.IssueFilterRequestDto;
import com.amos.silog.issue.dto.IssueRequestDto;
import com.amos.silog.issue.dto.IssueResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    String createIssue(IssueRequestDto issueRequestDto);

    IssueResponseDto getIssue(String id);

    void updateIssue(String id, IssueResponseDto issueResponseDto);

    void deleteIssue(String id);

    Page<IssueResponseDto> getFilteredIssues(IssueFilterRequestDto filters, Pageable pageable);
}
