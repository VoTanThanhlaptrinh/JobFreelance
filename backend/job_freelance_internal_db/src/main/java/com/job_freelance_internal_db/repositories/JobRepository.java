package com.job_freelance_internal_db.repositories;

import com.job_freelance_internal_db.model.Job;
import com.job_freelance_internal_db.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 13.1.8 Thực hiện gọi findByCreator(User creator, Pageable pageable)
//từ lớp JobRepository với user là thông tin creator nhà tuyển dụng đã đăng nhập trước đó
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByCreator(User creator, Pageable pageable);
}
