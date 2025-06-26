package com.jobportal.backend.dto;

public record HiringDto(
    Long id,
    String position,
    String company,
    String location,
    String requirements,
    String postedBy
) {}
