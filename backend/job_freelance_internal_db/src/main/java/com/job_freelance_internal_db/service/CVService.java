package com.job_freelance_internal_db.service;

import com.job_freelance_internal_db.object.CV;
import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Response;

public interface CVService {
    Response saveCV(CV cv);
    Job getJobById(long id);
}
