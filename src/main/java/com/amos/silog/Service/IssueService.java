package com.amos.silog.Service;


import com.amos.silog.Dto.IssueDto.IssueFilterRequestDto;
import com.amos.silog.Dto.IssueDto.RequestIssueDto;
import com.amos.silog.Dto.IssueDto.ResponseIssueDto;
import com.amos.silog.Entity.Issue;
import com.amos.silog.Exception.EntityConflictException;
import com.amos.silog.Exception.ResourceNotFoundException;
import com.amos.silog.Repository.IssueRepository;
import com.amos.silog.Repository.IssueSpecification;
import jakarta.persistence.OptimisticLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Long createIssue(RequestIssueDto requestIssueDto) {
        Issue issue = new Issue();
        issue.applyRequest(requestIssueDto);
        issueRepository.save(issue);
        return issue.getId();
    }

    public ResponseIssueDto getIssue(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        return issue.toResponseDto();
    }

    public void updateIssue(Long id, ResponseIssueDto responseIssueDto) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        RequestIssueDto requestDto = responseIssueDto.getRequestDto();
        try {
            issue.applyRequest(requestDto);
            issueRepository.save(issue);
        } catch (OptimisticLockException e) {
            throw new EntityConflictException("Issue");
        }
    }

    public void deleteIssue(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        issue.setDeleted(true);
        issueRepository.save(issue);
    }

    public Page<ResponseIssueDto> getFilteredIssues(IssueFilterRequestDto filters, Pageable pageable) {
        Specification<Issue> spec = IssueSpecification.withFilters(filters);
        return issueRepository.findAll(spec, pageable)
                .map(Issue::toResponseDto);
    }
}
