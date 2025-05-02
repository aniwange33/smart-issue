package com.amos.silog.Controller;


import com.amos.silog.Dto.IssueDto.RequestIssueDto;
import com.amos.silog.Dto.IssueDto.ResponseIssueDto;
import com.amos.silog.Service.AiSuggestedDescriptionService;
import com.amos.silog.Service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;
    private final AiSuggestedDescriptionService  ai;

    public IssueController(IssueService issueService, AiSuggestedDescriptionService ai) {
        this.issueService = issueService;
        this.ai = ai;
    }

    //  POST /api/issues/report: Create a new issue
    @PostMapping("/report")
    public ResponseEntity<URI> createIssue(@Valid @RequestBody RequestIssueDto dto) {
        long id = issueService.createIssue(dto);
        URI uri = URI.create("/api/v1/issues/" + id);
        return new ResponseEntity<>(uri, HttpStatus.CREATED);
    }

    //   POST /api/issues/ai/suggest: Get AI-based suggestion for issue description
    @PostMapping("/ai/suggest")
    public ResponseEntity<String> getAIBasedSuggestion(@RequestBody String description) {
         String refined  = ai.getDescriptionSuggestion(description);
        return ResponseEntity.ok(refined );
    }

    //  GET /api/issues/{id}: View issue details
    @GetMapping("/{id}")
    public ResponseEntity<ResponseIssueDto> getIssue(@PathVariable("id") Long id) {
        return ResponseEntity.ok(issueService.getIssue(id));
    }


    // TODO: Implement the following endpoints
    //    GET /api/issues: List all issues (with filtering options)
    //    PUT /api/issues/{id}: Update an issue
    //    DELETE /api/issues/{id}: Delete an issue
    //    POST /api/issues/upload: Upload files related to an issue

}
