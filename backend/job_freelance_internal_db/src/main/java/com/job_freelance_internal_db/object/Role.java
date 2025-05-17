package com.job_freelance_internal_db.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.job_freelance_internal_db.object.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Chỉ tính những trường có @EqualsAndHashCode.Include
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createDate;

    @LastModifiedDate
    @Column(updatable = true, insertable = false)
    private LocalDate updateDate;
}
