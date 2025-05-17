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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
