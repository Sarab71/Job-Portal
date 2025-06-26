package com.jobportal.backend.dto;

public record JobDto(
    Long id,
    String title,
    String description,
    String skills,
    String postedBy
) {}
