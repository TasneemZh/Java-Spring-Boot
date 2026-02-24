package com.tutorial.jobapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutorial.jobapi.entity.JobPost;

public interface JobPostRepository 
        extends JpaRepository<JobPost, Long> {

    List<JobPost> findByTitleContainingIgnoreCase(String title);
    List<JobPost> findBySalaryBetween(Double min, Double max);

    @Query("""
        SELECT j FROM JobPost j
        WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(j.company) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<JobPost> searchEverywhere(String keyword);
}
