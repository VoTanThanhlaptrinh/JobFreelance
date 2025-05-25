package com.job_freelance_internal_db.api;

import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.object.User;
import com.job_freelance_internal_db.object.dto.JobDTO;
import com.job_freelance_internal_db.repositories.JobRepository;
import com.job_freelance_internal_db.repositories.UserRepository;
import com.job_freelance_internal_db.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/job", produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class JobAPI {
    private final JobService jobService;
    @GetMapping("/get/newest/{page}")
    public ResponseEntity<Response<Object>> getNDataJobNewest(@PathVariable long page) {
        Response<Object>res =  jobService.getNDataJobNewest(page);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @GetMapping("/get/jobPost/{userId}")
    public ResponseEntity<Response<Object>> getJobPostOfUser(@PathVariable long userId) {
        Response<Object>res =  jobService.getJobApplyOfUser(userId);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @GetMapping("/get/apply/{userId}")
    public ResponseEntity<Response<Object>> getJobApplyOfUser(@PathVariable long userId) {
        Response<Object>res =  jobService.getJobApplyOfUser(userId);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    /*11.1.8: nhận request /api/job/postJob tại JobAPI, trích xuất dữ liệu từ body.*/
    @PostMapping("/postJob")
    //11.1.9: Tạo đối tượng JobDTO từ data của form rồi tiến hành kiểm tra dữ liệu hợp lệ
    public ResponseEntity<Response<Object>> saveJob(@Valid @RequestBody JobDTO jobDTO, BindingResult bindingResult) {
        //11.4.1: Bắt lỗi dữ liệu không hợp lệ khi tạo jobDTO.
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(404,null,bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        //11.1.10: Tạo đối tượng Job từ data của JobDTO qua Job job = jobDTO.toJob()
        Job job = jobDTO.toJob();
        //11.1.11: gọi phương thức saveJob(job): jobService để lưu job
        Response<Object> res = jobService.saveJob(job);
        //11.1.16: trả về một response thông báo đăng thành công
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
