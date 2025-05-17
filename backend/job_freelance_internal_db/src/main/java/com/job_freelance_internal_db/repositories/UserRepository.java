package com.job_freelance_internal_db.repositories;

import com.job_freelance_internal_db.object.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @EntityGraph(attributePaths = "roles")
    Optional<User> findUserByUsername(String username);
}
