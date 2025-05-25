package com.job_freelance_internal_db.service.impl;

import com.job_freelance_internal_db.model.Job;
import com.job_freelance_internal_db.model.Response;
import com.job_freelance_internal_db.model.User;
import com.job_freelance_internal_db.repositories.JobRepository;
import com.job_freelance_internal_db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class JobService implements com.job_freelance_internal_db.service.JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Value("${api.page.newest}")
    private long pageNewest;
    @Override
    public Response getNDataJobNewest(long page) {
        PageRequest pageRequest = PageRequest.of((int) page, (int) pageNewest).withSort(Sort.Direction.DESC, "createDate");
        return new Response(200,jobRepository.findAll(pageRequest),"success");
    }
//    13.1.7 Trỏ đến đến lớp JobService, gọi phương thức getJobPostOfUser(Principal principal, Pageable pageable)
//    để lấy danh sách các công việc đã đăng tuyển của nhà duyển dụng đó
    @Override
    public Response getJobPostOfUser(Principal principal, Pageable pageable) {
        Optional<User> userOptional = userRepository.findUserByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            return new Response(404, null, "Không tìm thấy người dùng.");
        }

        User user = userOptional.get();
//        13.1.10 Trả về Page<job> cho JobService, Page<job> được gán cho biến jobPosts
        Page<Job> jobPosts = jobRepository.findByCreator(user, pageable);

        if (jobPosts.isEmpty()) {
            return new Response(200, null, "Người dùng hiện chưa đăng công việc nào.");
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("jobs", jobPosts.getContent());
        responseData.put("totalPages", jobPosts.getTotalPages());
        responseData.put("totalElements", jobPosts.getTotalElements());
        responseData.put("currentPage", jobPosts.getNumber());
//        13.1.11 Thực hiện new Response(200, jobPosts, message), có danh sách công việc (nếu có), thông báo đi kèm, 200
        return new Response(200, responseData, "Lấy danh sách công việc thành công.");
    }



    @Override
    public Response getJobApplyOfUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.<Response>map(value -> new Response(200, value.getJobApplies(), "success")).orElseGet(() -> new Response(400, List.of(), "User not found"));

    }

    @Override
    public Response saveJob(Job job) {
        jobRepository.save(job);
        return new Response(200,null,"Success");
    }
}
