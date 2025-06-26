package com.jobportal.backend.repository;

import com.jobportal.backend.model.HiringPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiringPostRepository extends JpaRepository<HiringPost, Long> {}
