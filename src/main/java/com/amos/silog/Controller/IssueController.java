package com.amos.silog.Controller;


import com.amos.silog.Dto.RequestIssueDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {



    @PostMapping("/report")
    public ResponseEntity<?> createIssue(@Valid @RequestBody RequestIssueDto dto) {
       // Issue createdIssue = issueService.createIssue(issue);
        return new ResponseEntity<>("createdIssue", HttpStatus.CREATED);
    }




    // TODO: Implement the following endpoints
    //    GET /api/issues: List all issues (with filtering options)
    //    GET /api/issues/{id}: View issue details
    //    PUT /api/issues/{id}: Update an issue
    //    DELETE /api/issues/{id}: Delete an issue
    //    POST /api/issues/upload: Upload files related to an issue
    //    POST /api/issues/ai/suggest: Get AI-based suggestion for issue description
}
