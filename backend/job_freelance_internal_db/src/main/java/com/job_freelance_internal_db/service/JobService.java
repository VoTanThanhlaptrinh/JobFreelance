package com.job_freelance_internal_db.service;

import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Response;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;

public interface JobService {
    Response getNDataJobNewest(long page);
    Response getJobPostOfUser(Principal principal);
    Response getJobApplyOfUser(long userId);
    Response saveJob(Job job);
}
