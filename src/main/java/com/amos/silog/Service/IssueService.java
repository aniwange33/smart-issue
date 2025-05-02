package com.amos.silog.Service;


import com.amos.silog.Dto.IssueDto.IssueStatus;
import com.amos.silog.Dto.IssueDto.RequestIssueDto;
import com.amos.silog.Dto.IssueDto.ResponseIssueDto;
import com.amos.silog.Entity.Issue;
import com.amos.silog.Repository.IssueRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    private final IssueRepository issueRepository;



    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Long createIssue(RequestIssueDto requestIssueDto) {
        Issue issue = new Issue(requestIssueDto.getTitle(), IssueStatus.OPEN.name());
        issue.setDescription(requestIssueDto.getDescription());
        issue.setSeverityLevel(requestIssueDto.getSeverityLevel().name());
        issue.setAssignedTo(requestIssueDto.getAssignedTo());
        issue.setProject(requestIssueDto.getProject());
        issueRepository.save(issue);
        return issue.getId();
    }


    public ResponseIssueDto getIssue(Long id) {
        //handle this exception later the right
        Issue issue = issueRepository.findById(id).orElseThrow(() -> new RuntimeException("Issue not find with id: " + id));
        return ResponseIssueDto.builder()
                .title(issue.getTitle())
                .description(issue.getDescription())
                .id(issue.getId())
                .status(issue.getStatus())
                .project(issue.getProject())
                .logUrl(issue.getLogUrl())
                .assignedTo(issue.getAssignedTo())
                .severityLevel(issue. getSeverityLevel())
                .build();
    }
}
