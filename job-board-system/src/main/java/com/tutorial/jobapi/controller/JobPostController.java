package com.tutorial.jobapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tutorial.jobapi.dto.JobPostRequest;
import com.tutorial.jobapi.dto.JobPostResponse;
import com.tutorial.jobapi.entity.JobPost;
import com.tutorial.jobapi.service.JobPostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@Tag(name = "Job Posts", description = "Manage job postings")
public class JobPostController {

    private final JobPostService service;

    @PostMapping
    @Operation(summary = "Create a new job post")
    public ResponseEntity<JobPostResponse> create(
            @RequestBody JobPostRequest request) {
        return ResponseEntity.ok(toResponse(service.create(request)));
    }

    @GetMapping
    @Operation(summary = "Get all job posts (paginated)")
    public ResponseEntity<List<JobPostResponse>> getAll(
            @PageableDefault(size = 10) @ParameterObject Pageable pageable,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary) {
        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok(toResponseList(service.getByTitle(title)));
        }
        if (minSalary != null || maxSalary != null) {
            if (minSalary == null || maxSalary == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Both minSalary and maxSalary are required for salary range filtering");
            }
            if (minSalary > maxSalary) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "minSalary must be less than or equal to maxSalary");
            }
            return ResponseEntity.ok(toResponseList(service.getBySalaryRange(minSalary, maxSalary)));
        }
        return ResponseEntity.ok(toResponseList(service.getAll(pageable)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get job post by ID")
    public ResponseEntity<JobPostResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(toResponse(service.getById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a job post")
    public ResponseEntity<JobPostResponse> update(
            @PathVariable Long id,
            @RequestBody JobPostRequest request) {
        return ResponseEntity.ok(toResponse(service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a job post")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search job posts by keyword")
    public ResponseEntity<List<JobPostResponse>> search(
            @RequestParam String keyword) {
        return ResponseEntity.ok(toResponseList(service.search(keyword)));
    }

    private JobPostResponse toResponse(JobPost jobPost) {
        return JobPostResponse.fromEntity(jobPost);
    }

    private List<JobPostResponse> toResponseList(List<JobPost> jobPosts) {
        return jobPosts.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
