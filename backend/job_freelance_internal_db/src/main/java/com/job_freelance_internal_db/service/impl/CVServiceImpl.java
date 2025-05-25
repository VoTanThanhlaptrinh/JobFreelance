package com.job_freelance_internal_db.service.impl;

import com.job_freelance_internal_db.object.CV;
import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.repositories.CVRepository;
import com.job_freelance_internal_db.repositories.JobRepository;
import com.job_freelance_internal_db.service.CVService;
import org.springframework.stereotype.Service;

@Service
public class CVServiceImpl implements CVService {

    private final CVRepository cvRepository;
    private final JobRepository jobRepository;

    public CVServiceImpl(CVRepository cvRepository, JobRepository jobRepository) {
        this.cvRepository = cvRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public Response saveCV(CV cv) {
        try {
            cvRepository.save(cv);
            return new Response(200, null, "CV submitted successfully");
        } catch (Exception e) {
            return new Response(500, null, "Failed to save CV: " + e.getMessage());
        }
    }

    @Override
    public Job getJobById(long id) {
        return jobRepository.findById(id).get();
    }
}
