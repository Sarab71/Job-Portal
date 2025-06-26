package com.jobportal.backend.dto;

import jakarta.validation.constraints.*;

public record HiringRequest(
    @NotBlank String position,
    @NotBlank String company,
    @NotBlank String location,
    @NotBlank String requirements
) {}
