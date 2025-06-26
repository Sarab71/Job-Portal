package com.jobportal.backend.service;

import com.jobportal.backend.dto.JobDto;
import com.jobportal.backend.dto.JobRequest;
import com.jobportal.backend.model.Job;
import com.jobportal.backend.model.User;
import com.jobportal.backend.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> new JobDto(
                        job.getId(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getSkills(),
                        job.getPostedBy().getName()))
                .collect(Collectors.toList());
    }

    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSkills(),
                job.getPostedBy().getName());
    }

    public JobDto postJob(JobRequest request, User user) {
        Job job = new Job(
                request.title(),
                request.description(),
                request.skills(),
                user);

        Job saved = jobRepository.save(job);

        return new JobDto(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getSkills(),
                saved.getPostedBy().getName());
    }
}
