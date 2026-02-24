package com.tutorial.jobapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostRequest {
    private String title;
    private String description;
    private String company;
    private String location;
    private Double salary;
}
