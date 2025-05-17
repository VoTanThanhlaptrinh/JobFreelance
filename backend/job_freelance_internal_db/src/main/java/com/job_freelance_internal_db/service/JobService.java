package com.job_freelance_internal_db.service;

import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Response;

import java.util.List;

public interface JobService {
    Response<Object> getNDataJobNewest(long page);
    Response<Object> getJobPostOfUser(long userId);
    Response<Object> getJobApplyOfUser(long userId);
    Response<Object> saveJob(Job job);
}
