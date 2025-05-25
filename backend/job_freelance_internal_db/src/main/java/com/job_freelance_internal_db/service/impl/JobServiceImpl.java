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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
// 11.1.12: Spring tự inject thực thể JobServiceImpl để thực thi phương thức.
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    @Value("${api.page.newest}")
    private long pageNewest;
    @Override
    public Response<Object> getNDataJobNewest(long page) {
        PageRequest pageRequest = PageRequest.of((int) page, (int) pageNewest).withSort(Sort.Direction.DESC, "createDate");
        return new Response<>(200,jobRepository.findAll(pageRequest),"success");
    }

    @Override
    public Response<Object> getJobPostOfUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.<Response<Object>>map(value -> new Response<>(200, value.getJobPost(), "success")).orElseGet(() -> new Response<>(400, List.of(), "User not found"));
    }

    @Override
    public Response<Object> getJobApplyOfUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.<Response<Object>>map(value -> new Response<>(200, value.getJobApplies(), "success")).orElseGet(() -> new Response<>(400, List.of(), "User not found"));

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
