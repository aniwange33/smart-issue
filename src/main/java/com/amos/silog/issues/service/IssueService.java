package com.amos.silog.issues.service;

import com.amos.silog.issues.dto.IssueFilterRequestDto;
import com.amos.silog.issues.dto.IssueRequestDto;
import com.amos.silog.issues.dto.IssueResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    String createIssue(IssueRequestDto issueRequestDto);

    IssueResponseDto getIssue(String id);

    void updateIssue(String id, IssueResponseDto issueResponseDto);

    void deleteIssue(String id);

    Page<IssueResponseDto> getFilteredIssues(IssueFilterRequestDto filters, Pageable pageable);
}
