package com.job_freelance_internal_db.object.dto;

import com.job_freelance_internal_db.object.CV;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class CVDTO {
    @NotNull
    private String jobId;
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String desiredPosition;
    @NotNull
    private String skills;
    @NotNull
    private MultipartFile cvInput;

    public CVDTO(String jobId, String fullName, String email, String phone, String desiredPosition, String skills, MultipartFile cvInput) {
        this.jobId = jobId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.desiredPosition = desiredPosition;
        this.skills = skills;
        this.cvInput = cvInput;
    }

    public CVDTO(){

    }

    public @NotNull String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull String fullName) {
        this.fullName = fullName;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public @NotNull String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }

    public @NotNull String getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(@NotNull String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public @NotNull String getSkills() {
        return skills;
    }

    public void setSkills(@NotNull String skills) {
        this.skills = skills;
    }

    public @NotNull MultipartFile getCvInput() {
        return cvInput;
    }

    public void setCvInput(@NotNull MultipartFile cvInput) {
        this.cvInput = cvInput;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public CV toCV() throws IOException {
        CV cv = new CV();
        cv.setFullName(this.fullName);
        cv.setEmail(this.email);
        cv.setPhone(this.phone);
        cv.setDesiredPosition(this.desiredPosition);
        cv.setSkills(this.skills);

        if (cvInput != null && !cvInput.isEmpty()) {
            cv.setFileData(cvInput.getBytes());
            cv.setFileName(cvInput.getOriginalFilename());
            cv.setFileType(cvInput.getContentType());
        }

        return cv;
    }
}
