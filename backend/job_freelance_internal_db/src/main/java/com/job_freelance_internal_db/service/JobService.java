package com.job_freelance_internal_db.service;

import com.job_freelance_internal_db.model.Job;
import com.job_freelance_internal_db.model.Response;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface JobService {
    Response getNDataJobNewest(long page);
//    13.1.7 Trỏ đến đến lớp JobService, gọi phương thức getJobPostOfUser(Principal principal, Pageable pageable)
//    để lấy danh sách các công việc đã đăng tuyển của nhà duyển dụng đó
    Response getJobPostOfUser(Principal principal, Pageable pageable);
    Response getJobApplyOfUser(long userId);
    Response saveJob(Job job);
}
