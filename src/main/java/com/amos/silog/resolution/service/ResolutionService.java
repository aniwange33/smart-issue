package com.amos.silog.resolution.service;

import com.amos.silog.resolution.dto.ResolutionRequestDto;
import com.amos.silog.resolution.dto.ResolutionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing resolutions.
 */
public interface ResolutionService {

    /**
     * Create a new resolution
     * @param resolutionRequestDto the resolution data
     * @return the ID of the created resolution
     */
    Long createResolution(ResolutionRequestDto resolutionRequestDto);

    /**
     * Get a resolution by ID
     * @param id the resolution ID
     * @return the resolution
     */
    ResolutionResponseDto getResolution(Long id);

    /**
     * Get a resolution by issue ID
     * @param issueId the issue ID
     * @return the resolution
     */
    ResolutionResponseDto getResolutionByIssueId(Long issueId);

    /**
     * Update an existing resolution
     * @param id the resolution ID
     * @param resolutionRequestDto the updated resolution data
     */
    void updateResolution(Long id, ResolutionRequestDto resolutionRequestDto);

    /**
     * Delete a resolution
     * @param id the resolution ID
     */
    void deleteResolution(Long id);

    /**
     * Get all resolutions
     * @param pageable pagination information
     * @return paginated list of resolutions
     */
    Page<ResolutionResponseDto> getAllResolutions(Pageable pageable);

    /**
     * Get all resolutions by user ID
     * @param userId the user ID
     * @return list of resolutions
     */
    List<ResolutionResponseDto> getResolutionsByUserId(Long userId);

    /**
     * Get total time spent by user
     * @param userId the user ID
     * @return total time spent in hours
     */
    Double getTotalTimeSpentByUser(Long userId);

    /**
     * Process resolutions that need AI enhancement
     * @return number of resolutions processed
     */
    int processAiEnhancements();

    /**
     * Send notifications for resolved issues
     * @return number of notifications sent
     */
    int sendResolutionNotifications();
}