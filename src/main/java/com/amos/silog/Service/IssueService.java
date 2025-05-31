package com.amos.silog.Service;


import com.amos.silog.Dto.IssueDto.IssueFilterRequestDto;
import com.amos.silog.Dto.IssueDto.IssueRequestDto;
import com.amos.silog.Dto.IssueDto.IssueResponseDto;
import com.amos.silog.Entity.Issue;
import com.amos.silog.Exception.EntityConflictException;
import com.amos.silog.Exception.ResourceNotFoundException;
import com.amos.silog.Repository.IssueRepository;
import com.amos.silog.Repository.IssueSpecification;
import com.amos.silog.mapper.*;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueRequestConverter requestConverter;
    private final IssueResponseConverter responseConverter;
    private final IssueResponseToRequestConverter toRequestConverter;
    private final IssueUpdateConverter updateConverter;

    @Autowired
    public IssueService(IssueRepository repo, IssueConverters converters) {
        this.issueRepository = repo;
        this.requestConverter = converters.request;
        this.responseConverter = converters.response;
        this.toRequestConverter = converters.toRequest;
        this.updateConverter = converters.update;
    }

    public String createIssue(IssueRequestDto issueRequestDto) {
        Issue issue = requestConverter.apply(issueRequestDto);
        issueRepository.save(issue);
        return issue.getUuid();
    }

    public IssueResponseDto getIssue(String id) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        return responseConverter.apply(issue);
    }

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

    @Transactional
    public void deleteIssue(String id) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        issueRepository.delete(issue);
    }

    public Page<IssueResponseDto> getFilteredIssues(IssueFilterRequestDto filters, Pageable pageable) {
        Specification<Issue> spec = IssueSpecification.withFilters(filters);
        return issueRepository.findAll(spec, pageable)
                .map(responseConverter::apply);
    }
}
