package com.job_freelance_internal_db.object.dto;

import com.job_freelance_internal_db.object.Job;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDate;


public class JobDTO {
    @NotNull(message = "email is null")
    private String email;
    @NotNull(message = "title is null")
    private String title;
    @NotNull(message = "minSalary is null") @Min(value = 100, message = "Min salary min is 100 USD")
    private double minSalary;
    @NotNull(message = "maxSalary is null") @Max(value = 1500, message = "Max salary is 1500 USD")
    private double maxSalary;
    @NotNull(message = "minDuration is null") @Min(value = 5, message = "Min duration min is 5 days")
    private int minDuration;
    @NotNull(message = "maxDuration is null") @Max(value = 30, message = "Max duration is 30 days")
    private int maxDuration;
    @NotNull(message = "deadlineCV is null")
    private LocalDate deadlineCV;
    @NotNull(message = "description is null")
    private String description;
    @NotNull(message = "requirement is null")
    private String requirement;
    @NotNull(message = "skill is null")
    private String skill;
    @Lob
    private byte[] file;

    @AssertTrue
    public boolean isValidDeadline() {
        return LocalDate.now().isBefore(deadlineCV);
    }

    public JobDTO(String email, String title, double minSalary, double maxSalary, int minDuration, int maxDuration, LocalDate deadlineCV, String description, String requirement, String skill, byte[] file) {
        this.email = email;
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.deadlineCV = deadlineCV;
        this.description = description;
        this.requirement = requirement;
        this.skill = skill;
        this.file = file;
    }

    public JobDTO() {
    }

    public Job toJob() {
        Job job = new Job();
        job.setFile(file);
        job.setTitle(title);
        job.setDescription(description);
        job.setDeadlineCV(deadlineCV);
        job.setRequirement(requirement);
        job.setSkill(skill);
        job.setRangeDuration(String.format("%d - %d days", minDuration, maxDuration));
        job.setRangeSalary(String.format("%.1f - %.1f USD", minSalary, maxSalary));
        return job;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public LocalDate getDeadlineCV() {
        return deadlineCV;
    }

    public void setDeadlineCV(LocalDate deadlineCV) {
        this.deadlineCV = deadlineCV;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
