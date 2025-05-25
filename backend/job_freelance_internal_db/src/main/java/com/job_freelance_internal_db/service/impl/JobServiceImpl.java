package com.job_freelance_internal_db.service.impl;

import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.object.User;
import com.job_freelance_internal_db.repositories.JobRepository;
import com.job_freelance_internal_db.repositories.UserRepository;
import com.job_freelance_internal_db.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// 11.1.12: Spring tự inject thực thể JobServiceImpl để thực thi phương thức.
@Service

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobServiceImpl(JobRepository jobRepository, UserRepository userRepository) {
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

    public Response getJobPostOfUser(Principal principal, Pageable pageable) {
        Optional<User> userOptional = userRepository.findUserByUsername(principal.getName());
        if (userOptional.isEmpty()) {
            return new Response(404, null, "Không tìm thấy người dùng.");
        }

        User user = userOptional.get();
        Page<Job> jobPage = jobRepository.findByCreator(user, pageable);

        if (jobPage.isEmpty()) {
            return new Response(200, null, "Người dùng hiện chưa đăng công việc nào.");
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("jobs", jobPage.getContent());
        responseData.put("totalPages", jobPage.getTotalPages());
        responseData.put("totalElements", jobPage.getTotalElements());
        responseData.put("currentPage", jobPage.getNumber());

        return new Response(200, responseData, "Lấy danh sách công việc thành công.");
    }



    @Override
    public Response getJobApplyOfUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.<Response>map(value -> new Response(200, value.getJobApplies(), "success")).orElseGet(() -> new Response(400, List.of(), "User not found"));

    }

    @Override
    public Response<Object> saveJob(Job job) {
        //11.1.13: gọi phương thức save(job): jobRepository để lưu job xuống db
        jobRepository.save(job);
        //11.1.14: Tạo đối tượng Response thông báo lưu job thành công
        //11.1.15: trả về một response thông báo  đã lưu thành công
        return new Response<>(200,null,"Success");
    }
}
