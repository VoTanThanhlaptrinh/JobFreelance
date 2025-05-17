package com.job_freelance_internal_db.repositories;

import com.job_freelance_internal_db.object.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository extends JpaRepository<CV, Long> {
}
