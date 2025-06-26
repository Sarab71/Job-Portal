package com.jobportal.backend.service;

import com.jobportal.backend.dto.HiringDto;
import com.jobportal.backend.dto.HiringRequest;
import com.jobportal.backend.model.HiringPost;
import com.jobportal.backend.model.User;
import com.jobportal.backend.repository.HiringPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HiringPostService {

    @Autowired
    private HiringPostRepository hiringPostRepository;

    public HiringDto postHiring(HiringRequest request, User employer) {
    HiringPost post = new HiringPost(
        request.position(),
        request.company(),
        request.location(),
        request.requirements(),
        employer
    );

    HiringPost saved = hiringPostRepository.save(post);

    return new HiringDto(
        saved.getId(),
        saved.getPosition(),
        saved.getCompany(),
        saved.getLocation(),
        saved.getRequirements(),
        saved.getPostedBy().getName()
    );
}

}
