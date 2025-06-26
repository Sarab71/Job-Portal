package com.jobportal.backend.controller;

import com.jobportal.backend.dto.JobDto;
import com.jobportal.backend.dto.JobRequest;
import com.jobportal.backend.model.User;
import com.jobportal.backend.repository.UserRepository;
import com.jobportal.backend.service.JobService;
import com.jobportal.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<JobDto> postJob(@RequestBody @Valid JobRequest request, HttpServletRequest httpRequest) {
        // 🔐 Extract token from header
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build(); // unauthorized
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        // 🔍 Find user from DB
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Pass user to service
        JobDto job = jobService.postJob(request, user);
        return ResponseEntity.ok(job);
    }
        @GetMapping("/all")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }
}
