package com.amos.silog.resolution.repository;

import com.amos.silog.issue.model.Issue;
import com.amos.silog.resolution.model.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Resolution} entity.
 * Provides methods to interact with the resolutions table in the database.
 */
@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Long> {

    /**
     * Find resolution by issue
     * @param issue the issue
     * @return the resolution if found
     */
    Optional<Resolution> findByIssue(Issue issue);

    /**
     * Find resolution by issue id
     * @param issueId the issue id
     * @return the resolution if found
     */
    Optional<Resolution> findByIssueId(Long issueId);

    /**
     * Find all resolutions by resolved by user id
     * @param userId the user id
     * @return list of resolutions
     */
    List<Resolution> findByResolvedById(Long userId);

    /**
     * Find all resolutions where notification has not been sent
     * @return list of resolutions
     */
    List<Resolution> findByNotificationSentFalse();

    /**
     * Find total time spent by user on resolutions
     * @param userId the user id
     * @return the total time spent
     */
    @Query("SELECT SUM(r.timeSpentHours) FROM Resolution r WHERE r.resolvedBy.id = :userId")
    Double findTotalTimeSpentByUserId(@Param("userId") Long userId);
}