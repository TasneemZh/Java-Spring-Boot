package com.tutorial.jobapi.dto;

import java.time.LocalDateTime;

import com.tutorial.jobapi.entity.JobPost;

public record JobPostResponse(
        Long id,
        String title,
        String description,
        String company,
        String location,
        Double salary,
        LocalDateTime createdAt) {

    public static JobPostResponse fromEntity(JobPost jobPost) {
        return new JobPostResponse(
                jobPost.getId(),
                jobPost.getTitle(),
                jobPost.getDescription(),
                jobPost.getCompany(),
                jobPost.getLocation(),
                jobPost.getSalary(),
                jobPost.getCreatedAt());
    }
}
