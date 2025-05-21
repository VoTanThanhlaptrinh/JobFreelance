package com.job_freelance_internal_db.object;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String rangeSalary;
    private String rangeDuration;
    private LocalDate deadlineCV;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String requirement;
    @Column(columnDefinition = "TEXT")
    private String skill;
    @Lob
    private byte[] file;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "applies_id")
    private User applies;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createDate;
    @LastModifiedDate
    @Column(updatable = true, insertable = false)
    private LocalDate updateDate;

    public Job(String title, String rangeSalary, String rangeDuration, String description, String requirement, LocalDate deadlineCV, String skill, byte[] file, User creator, User applies, LocalDate createDate, LocalDate updateDate) {
        this.title = title;
        this.rangeSalary = rangeSalary;
        this.rangeDuration = rangeDuration;
        this.description = description;
        this.requirement = requirement;
        this.deadlineCV = deadlineCV;
        this.skill = skill;
        this.file = file;
        this.creator = creator;
        this.applies = applies;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Job() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRangeSalary() {
        return rangeSalary;
    }

    public void setRangeSalary(String rangeSalary) {
        this.rangeSalary = rangeSalary;
    }

    public String getRangeDuration() {
        return rangeDuration;
    }

    public void setRangeDuration(String rangeDuration) {
        this.rangeDuration = rangeDuration;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getApplies() {
        return applies;
    }

    public void setApplies(User applies) {
        this.applies = applies;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
