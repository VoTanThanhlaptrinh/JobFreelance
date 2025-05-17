package com.job_freelance_internal_db.repositories;

import com.job_freelance_internal_db.object.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
