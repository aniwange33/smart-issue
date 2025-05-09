package com.amos.silog.Repository;

import com.amos.silog.Entity.Issue;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;

/**
 * Repository interface for the IssueTable entity.
 * Provides methods to perform CRUD operations on IssueTable entities.
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>, JpaSpecificationExecutor<Issue> {

    /**
     * Find issues by status.
     *
     * @param status the status to search for
     * @return a list of issues with the given status
     */
    List<Issue> findByStatus(String status);

    /**
     * Find issues by assigned user.
     *
     * @param assignedTo the user to whom issues are assigned
     * @return a list of issues assigned to the given user
     */
    List<Issue> findByAssignedTo(String assignedTo);


    @Query("SELECT i FROM Issue i WHERE i.uuid = :uuid")
    @QueryHints({
            @QueryHint(name = HINT_CACHEABLE, value = "true")
    })
    Optional<Issue> getIssueByUuid(@Param("uuid") String uuid);

    /**
     * Find issues by severity level.
     *
     * @param severityLevel the severity level to search for
     * @return a list of issues with the given severity level
     */
    List<Issue> findBySeverityLevel(String severityLevel);

    /**
     * Find issues containing the given title (case-insensitive).
     *
     * @param title the title to search for
     * @return a list of issues with titles containing the given string
     */
    List<Issue> findByTitleContainingIgnoreCase(String title);
}