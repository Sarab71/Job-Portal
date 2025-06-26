package com.jobportal.backend.dto;

import com.jobportal.backend.model.User;

public record RegisterRequest(String name, String email, String password, User.Role role) {}
