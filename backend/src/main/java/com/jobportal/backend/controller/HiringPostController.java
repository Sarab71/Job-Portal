package com.jobportal.backend.controller;

import com.jobportal.backend.dto.HiringDto;
import com.jobportal.backend.dto.HiringRequest;
import com.jobportal.backend.model.User;
import com.jobportal.backend.repository.UserRepository;
import com.jobportal.backend.service.HiringPostService;
import com.jobportal.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hirings")
public class HiringPostController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HiringPostService hiringPostService;

    @PostMapping
    public ResponseEntity<HiringDto> postHiring(@RequestBody @Valid HiringRequest request, HttpServletRequest httpRequest) {
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        User employer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        HiringDto post = hiringPostService.postHiring(request, employer);
        return ResponseEntity.ok(post);
    }
}
