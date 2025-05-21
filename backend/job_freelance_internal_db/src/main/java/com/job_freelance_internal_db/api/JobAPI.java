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

@Slf4j
public class JobAPI {
    private final JobService jobService;

    public JobAPI(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/get/newest/{page}")
    public ResponseEntity<Response> getNDataJobNewest(@PathVariable long page) {
        Response res =  jobService.getNDataJobNewest(page);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @GetMapping("/get/jobPost/{userId}")
    public ResponseEntity<Response> getJobPostOfUser(@PathVariable long userId) {
        Response res =  jobService.getJobApplyOfUser(userId);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @GetMapping("/get/apply/{userId}")
    public ResponseEntity<Response> getJobApplyOfUser(@PathVariable long userId) {
        Response res =  jobService.getJobApplyOfUser(userId);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @PostMapping("/postJob")
    public ResponseEntity<Response> saveJob(@Valid @RequestBody JobDTO jobDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(404,null,bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        Response res = jobService.saveJob(jobDTO.toJob());
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
