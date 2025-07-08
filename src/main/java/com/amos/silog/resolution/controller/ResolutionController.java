package com.amos.silog.resolution.controller;

import com.amos.silog.resolution.dto.ResolutionRequestDto;
import com.amos.silog.resolution.dto.ResolutionResponseDto;
import com.amos.silog.resolution.service.ResolutionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resolutions")
@Slf4j
@Tag(name = "Resolutions", description = "Resolution management endpoints")
public class ResolutionController {
    private final ResolutionService resolutionService;
    public ResolutionController(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    // POST /api/v1/resolutions: Create a new resolution
    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<URI> createResolution(@Valid @RequestBody ResolutionRequestDto dto) {
        Long id = resolutionService.createResolution(dto);
        URI location = URI.create("/api/v1/resolutions/" + id);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    // GET /api/v1/resolutions/{id}: Get resolution by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','DEVELOPER')")
    public ResponseEntity<ResolutionResponseDto> getResolution(@PathVariable("id") Long id) {
        return ResponseEntity.ok(resolutionService.getResolution(id));
    }

    // GET /api/v1/resolutions/issue/{issueId}: Get resolution by Issue ID
    @GetMapping("/issue/{issueId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','DEVELOPER')")
    public ResponseEntity<ResolutionResponseDto> getResolutionByIssueId(@PathVariable("issueId") Long issueId) {
        return ResponseEntity.ok(resolutionService.getResolutionByIssueId(issueId));
    }

    // PUT /api/v1/resolutions/{id}: Update a resolution
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> updateResolution(@PathVariable("id") Long id, @Valid @RequestBody ResolutionRequestDto dto) {
        resolutionService.updateResolution(id, dto);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/v1/resolutions/{id}: Delete a resolution
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Void> deleteResolution(@PathVariable("id") Long id) {
        resolutionService.deleteResolution(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/v1/resolutions: Get all resolutions (paginated)
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN','DEVELOPER')")
    public ResponseEntity<Page<ResolutionResponseDto>> getAllResolutions(
            @PageableDefault(size = 50, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ResolutionResponseDto> page = resolutionService.getAllResolutions(pageable);
        return ResponseEntity.ok(page);
    }

    // GET /api/v1/resolutions/user/{userId}: Get resolutions by user
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ResolutionResponseDto>> getResolutionsByUserId(@PathVariable("userId") Long userId) {
        List<ResolutionResponseDto> resolutions = resolutionService.getResolutionsByUserId(userId);
        return ResponseEntity.ok(resolutions);
    }

    // GET /api/v1/resolutions/user/{userId}/total-time: Total time spent by user
    @GetMapping("/user/{userId}/total-time")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Double> getTotalTimeSpentByUser(@PathVariable("userId") Long userId) {
        Double totalTime = resolutionService.getTotalTimeSpentByUser(userId);
        return ResponseEntity.ok(totalTime);
    }

    // POST /api/v1/resolutions/process-ai: Trigger AI enhancement process
    @PostMapping("/process-ai")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> processAiEnhancements() {
        int processed = resolutionService.processAiEnhancements();
        return ResponseEntity.ok(processed);
    }

    // POST /api/v1/resolutions/send-notifications: Send resolution notifications
    @PostMapping("/send-notifications")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> sendResolutionNotifications() {
        int sent = resolutionService.sendResolutionNotifications();
        return ResponseEntity.ok(sent);
    }
}
