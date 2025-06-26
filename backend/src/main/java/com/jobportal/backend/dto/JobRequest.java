package com.jobportal.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record JobRequest(
    @NotBlank String title,
    @NotBlank String description,
    @NotBlank String skills
) {}
