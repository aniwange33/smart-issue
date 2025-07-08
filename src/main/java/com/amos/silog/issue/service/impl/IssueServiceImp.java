package com.amos.silog.issue.service.impl;


import com.amos.silog.annotation.IssueLogger;
import com.amos.silog.auth.service.AuthService;
import com.amos.silog.common.exception.EntityConflictException;
import com.amos.silog.common.exception.ResourceNotFoundException;
import com.amos.silog.issue.dto.IssueFilterRequestDto;
import com.amos.silog.issue.dto.IssueRequestDto;
import com.amos.silog.issue.dto.IssueResponseDto;
import com.amos.silog.issue.mapper.*;
import com.amos.silog.issue.model.Issue;
import com.amos.silog.issue.repository.IssueRepository;
import com.amos.silog.issue.repository.IssueSpecification;
import com.amos.silog.issue.service.IssueService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueServiceImp  implements IssueService {
    private final IssueRepository issueRepository;
    private final IssueRequestConverter requestConverter;
    private final IssueResponseConverter responseConverter;
    private final IssueResponseToRequestConverter toRequestConverter;
    private final IssueUpdateConverter updateConverter;

    @Autowired
    public IssueServiceImp(IssueRepository repo, IssueConverters converters, AuthService authService) {
        this.issueRepository = repo;
        this.requestConverter = converters.request;
        this.responseConverter = converters.response;
        this.toRequestConverter = converters.toRequest;
        this.updateConverter = converters.update;
    }

    @Override
    @Transactional
    public String createIssue(IssueRequestDto issueRequestDto) {
        Issue issue = requestConverter.apply(issueRequestDto);
        issueRepository.save(issue);
        return issue.getUuid();
    }
    @Override
    @IssueLogger(name = "getIssue")
    public IssueResponseDto getIssue(String id) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        return responseConverter.apply(issue);
    }
    @Override
    @Transactional
    public void updateIssue(String id, IssueResponseDto issueResponseDto) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        IssueRequestDto requestDto = toRequestConverter.apply(issueResponseDto);
        try {
            updateConverter.updateEntity(issue, requestDto);
        } catch (OptimisticLockException e) {
            throw new EntityConflictException(issue.getTitle());
        }
    }

    @Override
    @Transactional
    public void deleteIssue(String id) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        issueRepository.delete(issue);
    }

    @Override
    public Page<IssueResponseDto> getFilteredIssues(IssueFilterRequestDto filters, Pageable pageable) {
        Specification<Issue> spec = IssueSpecification.withFilters(filters);
        return issueRepository.findAll(spec, pageable)
                .map(responseConverter::apply);
    }
}
