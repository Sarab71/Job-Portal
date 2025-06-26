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

    // ✅ POST a new job
    @PostMapping
    public ResponseEntity<JobDto> postJob(@RequestBody @Valid JobRequest request, HttpServletRequest httpRequest) {
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        JobDto job = jobService.postJob(request, user);
        return ResponseEntity.ok(job);
    }

    // ✅ GET all jobs
    @GetMapping("/all")
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // ✅ GET job by ID (for Job Details Page)
    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        JobDto jobDto = jobService.getJobById(id);
        return ResponseEntity.ok(jobDto);
    }
}
