package com.amos.silog.Entity;

import com.amos.silog.Repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BaseEntityTest {

    @Autowired
    private IssueRepository issueRepository;

    @Test
    public void testBaseEntityFields() {
        // Create a new issue
        Issue issue = new Issue()
                .withDescription("This is a test issue to verify BaseEntity functionality")
                .withSeverityLevel("Medium")
               .withStatus("Open")
                .withTitle("Test Issue");

        // Save the issue
        Issue savedIssue = issueRepository.save(issue);

        // Verify that the ID was generated
        assertNotNull(savedIssue.getId());

        // Verify that the audit fields were populated
        assertNotNull(savedIssue.getCreatedAt());
        assertNotNull(savedIssue.getUpdatedAt());
        assertEquals("system", savedIssue.getCreatedBy());
        assertEquals("system", savedIssue.getUpdatedBy());

        // Verify that createdAt and updatedAt are close to the current time
        LocalDateTime now = LocalDateTime.now();
        assertTrue(savedIssue.getCreatedAt().isAfter(now.minusMinutes(1)));
        assertTrue(savedIssue.getUpdatedAt().isAfter(now.minusMinutes(1)));

        // Update the issue
        savedIssue.withStatus("In Progress");
        Issue updatedIssue = issueRepository.save(savedIssue);

        // Verify that updatedAt was updated but createdAt remains the same
        assertEquals(savedIssue.getCreatedAt(), updatedIssue.getCreatedAt());
        assertTrue(updatedIssue.getUpdatedAt().isAfter(savedIssue.getUpdatedAt()) ||
                updatedIssue.getUpdatedAt().equals(savedIssue.getUpdatedAt()));
    }
}