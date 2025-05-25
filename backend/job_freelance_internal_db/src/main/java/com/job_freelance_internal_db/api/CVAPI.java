package com.job_freelance_internal_db.api;

import com.job_freelance_internal_db.object.CV;
import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.object.dto.CVDTO;
import com.job_freelance_internal_db.service.CVService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/cv", produces = "application/json")
public class CVAPI {

    private final CVService cvService;

    public CVAPI(CVService cvService) {
        this.cvService = cvService;
    }

    // Nhận dữ liệu hồ sơ xin việc gửi lên từ client (form dữ liệu CV) rồi lưu vào hệ thống.
    @PostMapping("/submit")
    public ResponseEntity<Response> submitCV(@Valid @ModelAttribute CVDTO cvDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(400, null, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        try {
            Job job = cvService.getJobById(Integer.parseInt(cvDTO.getJobId()));
            CV cv = cvDTO.toCV();
            cv.setJob(job);
            Response res = cvService.saveCV(cv);
            return ResponseEntity.status(res.getStatus()).body(res);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(500, null, "Error processing CV file: " + e.getMessage()));
        }
    }
}
