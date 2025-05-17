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

@Data
@AllArgsConstructor
@NoArgsConstructor
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
        return LocalDate.now().isAfter(deadlineCV);
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
}
