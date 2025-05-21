package com.job_freelance_internal_db.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullName;
    private LocalDate birthDate;
    private String phoneNumber;

    private boolean accountLocked;
    private boolean enabled;
    private boolean active;

    // Quan hệ ManyToMany với Role
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user_token")
    private List<Token> tokens;

    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<Job> jobPost;

    @OneToMany(mappedBy = "applies")
    @JsonIgnore
    private List<Job> jobApplies;

    @OneToMany(mappedBy = "user_cv")
    private List<CV> cvs;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime creationDate;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    // Cấp quyền từ Role cho Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setActive(boolean active) {
        this.active = active;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }



    public void setJobPost(List<Job> jobPost) {
        this.jobPost = jobPost;
    }



    public void setJobApplies(List<Job> jobApplies) {
        this.jobApplies = jobApplies;
    }


    public void setCvs(List<CV> cvs) {
        this.cvs = cvs;
    }



    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }



    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public List<Job> getJobApplies() {
        return jobApplies;
    }

    public List<Job> getJobPost() {
        return jobPost;
    }
}
