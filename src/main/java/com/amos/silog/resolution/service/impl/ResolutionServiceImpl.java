package com.amos.silog.resolution.service.impl;

import com.amos.silog.common.exception.EntityConflictException;
import com.amos.silog.common.exception.ResourceNotFoundException;
import com.amos.silog.issue.dto.IssueStatus;
import com.amos.silog.issue.model.Issue;
import com.amos.silog.issue.repository.IssueRepository;
import com.amos.silog.resolution.dto.ResolutionRequestDto;
import com.amos.silog.resolution.dto.ResolutionResponseDto;
import com.amos.silog.resolution.mapper.ResolutionConverters;
import com.amos.silog.resolution.model.Resolution;
import com.amos.silog.resolution.repository.ResolutionRepository;
import com.amos.silog.resolution.service.ResolutionService;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the ResolutionService interface.
 */
@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {
    private final ResolutionRepository resolutionRepository;
    private final IssueRepository issueRepository;
    private final ResolutionConverters converters;

    @Override
    @Transactional
    public Long createResolution(ResolutionRequestDto resolutionRequestDto) {
        // Check if issue exists
        Issue issue = issueRepository.findById(resolutionRequestDto.getIssueId())
                .orElseThrow(() -> new ResourceNotFoundException("Issue not found with id: " + resolutionRequestDto.getIssueId()));
        // Check if resolution already exists for this issue
        if (resolutionRepository.findByIssue(issue).isPresent()) {
            throw new EntityConflictException("Resolution already exists for issue: " + issue.getId());
        }
        // Create resolution
        Resolution resolution = converters.request.apply(resolutionRequestDto);
        converters.request.withIssue(resolution, issue);
        // Set resolved_at if not provided
        if (resolution.getResolvedAt() == null) {
            resolution.withResolvedAt(LocalDateTime.now());
        }
        // Save resolution
        Resolution savedResolution = resolutionRepository.save(resolution);
        issue.withStatus(IssueStatus.IN_PROGRESS.name());
        issueRepository.save(issue);
        return savedResolution.getId();
    }

    @Override
    public ResolutionResponseDto getResolution(Long id) {
        Resolution resolution = resolutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution not found with id: " + id));
        return converters.response.apply(resolution);
    }

    @Override
    public ResolutionResponseDto getResolutionByIssueId(Long issueId) {
        Resolution resolution = resolutionRepository.findByIssueId(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution not found for issue id: " + issueId));
        return converters.response.apply(resolution);
    }

    @Override
    @Transactional
    public void updateResolution(Long id, ResolutionRequestDto resolutionRequestDto) {
        Resolution resolution = resolutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution not found with id: " + id));
        try {
            converters.request.update(resolution, resolutionRequestDto);
            resolutionRepository.save(resolution);
        } catch (OptimisticLockException e) {
            throw new EntityConflictException("Resolution was updated by another user");
        }
    }

    @Override
    @Transactional
    public void deleteResolution(Long id) {
        Resolution resolution = resolutionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resolution not found with id: " + id));
        resolutionRepository.delete(resolution);
    }

    @Override
    public Page<ResolutionResponseDto> getAllResolutions(Pageable pageable) {
        return resolutionRepository.findAll(pageable)
                .map(converters.response::apply);
    }

    @Override
    public List<ResolutionResponseDto> getResolutionsByUserId(Long userId) {
        return resolutionRepository.findByResolvedById(userId).stream()
                .map(converters.response::apply)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalTimeSpentByUser(Long userId) {
        return resolutionRepository.findTotalTimeSpentByUserId(userId);
    }

    @Override
    @Transactional
    public int processAiEnhancements() {
        // This would be implemented with actual AI integration
        // For now, just a placeholder
        return 0;
    }

    @Override
    @Transactional
    public int sendResolutionNotifications() {
        // Find resolutions where notification has not been sent
        List<Resolution> unnotifiedResolutions = resolutionRepository.findByNotificationSentFalse();

        // For each resolution, send notification and mark as sent
        // This would be implemented with actual notification service
        for (Resolution resolution : unnotifiedResolutions) {
            // Send notification logic would go here

            // Mark notification as sent
            resolution.withNotificationSent(true);
            resolutionRepository.save(resolution);
        }

        return unnotifiedResolutions.size();
    }


}