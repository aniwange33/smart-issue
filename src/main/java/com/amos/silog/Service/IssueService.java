package com.amos.silog.Service;


import com.amos.silog.Dto.IssueDto.IssueFilterRequestDto;
import com.amos.silog.Dto.IssueDto.IssueRequestDto;
import com.amos.silog.Dto.IssueDto.IssueResponseDto;
import com.amos.silog.Entity.Issue;
import com.amos.silog.Exception.EntityConflictException;
import com.amos.silog.Exception.ResourceNotFoundException;
import com.amos.silog.Repository.IssueRepository;
import com.amos.silog.Repository.IssueSpecification;
import com.amos.silog.mapper.IssueRequestConverter;
import com.amos.silog.mapper.IssueResponseConverter;
import com.amos.silog.mapper.IssueResponseToRequestConverter;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueRequestConverter requestConverter;
    private final IssueResponseConverter responseConverter;
    private final IssueResponseToRequestConverter toRequestConverter;

    @Autowired
    public IssueService(IssueRepository issueRepository,
                        IssueRequestConverter issueRequestMapper,
                        IssueResponseConverter issueResponseMapper,
                        IssueResponseToRequestConverter responseToRequestMapper) {
        this.issueRepository = issueRepository;
        this.requestConverter = issueRequestMapper;
        this.responseConverter = issueResponseMapper;
        this.toRequestConverter = responseToRequestMapper;
    }

    public String  createIssue(IssueRequestDto issueRequestDto) {
        Issue issue = requestConverter.apply(issueRequestDto);
        issueRepository.save(issue);
        return issue.getUuid();
    }

    public IssueResponseDto getIssue(String id) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        return responseConverter.apply(issue);
    }

    public void updateIssue(String id, IssueResponseDto issueResponseDto) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        IssueRequestDto requestDto = toRequestConverter.apply(issueResponseDto);
        try {
            requestConverter.apply(requestDto);
            issueRepository.save(issue);
        } catch (OptimisticLockException e) {
            throw new EntityConflictException("Issue");
        }
    }

    public void deleteIssue(String  id) {
        Issue issue = issueRepository.getIssueByUuid(id).orElseThrow(() -> new ResourceNotFoundException("Issue not find with id: " + id));
        issue.setDeleted(true);
        issueRepository.save(issue);
    }

    public Page<IssueResponseDto> getFilteredIssues(IssueFilterRequestDto filters, Pageable pageable) {
        Specification<Issue> spec = IssueSpecification.withFilters(filters);
        return issueRepository.findAll(spec, pageable)
                .map(responseConverter::apply);
    }
}
