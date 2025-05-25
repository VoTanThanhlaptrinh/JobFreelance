package com.job_freelance_internal_db.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user_cv;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createDate;
    @LastModifiedDate
    @Column(updatable = true, insertable = false)
    private LocalDate updateDate;
}
