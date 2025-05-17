package com.job_freelance_internal_db.repositories;

import com.job_freelance_internal_db.object.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
