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
    @PostMapping("/postJob")
    public ResponseEntity<Response<Object>> saveJob(@Valid @RequestBody JobDTO jobDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(404,null,bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        Response<Object> res = jobService.saveJob(jobDTO.toJob());
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
