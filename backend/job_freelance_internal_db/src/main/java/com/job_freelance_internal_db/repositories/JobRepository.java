package com.job_freelance_internal_db.repositories;

import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByCreator(User creator, Pageable pageable);

}
