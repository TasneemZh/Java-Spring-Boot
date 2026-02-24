package com.tutorial.jobapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tutorial.jobapi.dto.JobPostRequest;
import com.tutorial.jobapi.entity.JobPost;
import com.tutorial.jobapi.repository.JobPostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository repository;

    public JobPost create(JobPostRequest request) {
        JobPost job = JobPost.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .company(request.getCompany())
                .location(request.getLocation())
                .salary(request.getSalary())
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(job);
    }

    public List<JobPost> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public JobPost getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
    }

    public List<JobPost> getByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<JobPost> getBySalaryRange(Double min, Double max) {
        return repository.findBySalaryBetween(min, max);
    }

    public JobPost update(Long id, JobPostRequest request) {
        JobPost job = getById(id);
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        return repository.save(job);
    }

    public void delete(Long id) {
        JobPost job = getById(id);
        repository.delete(job);
    }

    public List<JobPost> search(String keyword) {
        return repository.searchEverywhere(keyword);
    }
}
