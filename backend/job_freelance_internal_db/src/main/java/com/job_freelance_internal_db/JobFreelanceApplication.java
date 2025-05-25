package com.job_freelance_internal_db;

import com.job_freelance_internal_db.object.Job;
import com.job_freelance_internal_db.object.Role;
import com.job_freelance_internal_db.object.User;
import com.job_freelance_internal_db.repositories.JobRepository;
import com.job_freelance_internal_db.repositories.RoleRepository;
import com.job_freelance_internal_db.repositories.UserRepository;
import jakarta.persistence.EntityListeners;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class JobFreelanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobFreelanceApplication.class, args);
    }
    @Bean
    @Transactional
    CommandLineRunner initRoles(PasswordEncoder encoder, RoleRepository roleRepository, UserRepository userRepository, JobRepository jobRepository) {
        return args -> {
            List<String> roleNames = List.of("ROLE_USER", "ROLE_ADMIN");

            for (String roleName : roleNames) {
                if (roleRepository.findByName(roleName).isEmpty()) {
                    Role role = new Role();
                    role.setName(roleName);
                    roleRepository.saveAndFlush(role);
                }
            }
            User user = new User();
            user.setUsername("22130257@st.hcmuaf.edu.vn");
            user.setPassword(encoder.encode("12345678"));
            user.setActive(true);
            Role role = roleRepository.findByName("ROLE_USER").get();
            user.setRoles(new HashSet<>());
            user.getRoles().add(role);
            userRepository.saveAndFlush(user);

            List<Job> jobList = new ArrayList<>();

            jobList.add(new Job(
                    "Web Developer for E-commerce Site", "1000 - 1500 USD", "20 - 30 days",
                    "Develop a responsive e-commerce website using modern web technologies.",
                    "Proficiency in HTML, CSS, JavaScript. Experience with payment gateways.",
                    LocalDate.now().plusDays(30),
                    "HTML, CSS, JavaScript, React, Node.js",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Logo Design for Tech Startup", "200 - 400 USD", "3 - 7days",
                    "Create a modern and professional logo for a new tech company.",
                    "Portfolio of past logo works required.",
                    LocalDate.now().plusDays(7),
                    "Adobe Illustrator, Figma, Branding",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Content Writer for Blog Articles", "300 - 500 USD", " 10 - 14 days",
                    "Write SEO-optimized blog posts on tech and startups.",
                    "Fluent English, good research skills, SEO knowledge.",
                    LocalDate.now().plusDays(14),
                    "Content Writing, SEO, WordPress",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Mobile App Testing (QA)", "400 - 600 USD", "10 - 20 days",
                    "Test a cross-platform mobile app, report bugs and UX issues.",
                    "Experience with manual testing. Familiarity with Android & iOS.",
                    LocalDate.now().plusDays(20),
                    "QA, Testing, Android, iOS",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Translate Documents (English-Vietnamese)", "150 - 300 USD", " 6 - 10 days",
                    "Translate legal and technical documents to Vietnamese.",
                    "Native Vietnamese, fluent English, translation experience.",
                    LocalDate.now().plusDays(10),
                    "Translation, English, Vietnamese",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Social Media Marketing Freelancer", "500 - 800 USD", "17 - 25 days",
                    "Manage Instagram and Facebook posts for a lifestyle brand.",
                    "Experience in social media growth strategies.",
                    LocalDate.now().plusDays(25),
                    "Facebook Ads, Instagram, Canva, Copywriting",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Backend Developer (Spring Boot API)", "1200 - 1800 USD", " 5 - 30 days",
                    "Develop RESTful APIs for mobile app backend using Spring Boot.",
                    "Strong Java skills, knowledge of JWT, OAuth2.",
                    LocalDate.now().plusDays(30),
                    "Java, Spring Boot, MySQL, JWT",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "UI/UX Design for Mobile App", "700 - 1000 USD", " 18 - 21 days",
                    "Design user-friendly mobile interfaces for a fitness tracking app.",
                    "Portfolio required. Experience with user flow and wireframing.",
                    LocalDate.now().plusDays(21),
                    "Figma, Adobe XD, UX Research",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Video Editor for YouTube Channel", "300 - 600 USD", " 8 - 14 days",
                    "Edit short-form YouTube videos with transitions and effects.",
                    "Must deliver high-quality videos under tight deadlines.",
                    LocalDate.now().plusDays(14),
                    "Adobe Premiere, After Effects, YouTube SEO",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Database Optimization Consultant", "800 - 1200 USD", "20 - 30 days",
                    "Audit and optimize MySQL database performance for a SaaS platform.",
                    "Experience in indexing, query optimization, and profiling.",
                    LocalDate.now().plusDays(30),
                    "MySQL, Database Design, Performance Tuning",
                    null, user, null, LocalDate.now(), null));

            jobList.add(new Job(
                    "Web Developer for E-commerce Site", "1000 - 1500 USD", "20 - 30 days",
                    "Develop a responsive e-commerce website using modern web technologies.",
                    "Proficiency in HTML, CSS, JavaScript. Experience with payment gateways.",
                    LocalDate.now().plusDays(30),
                    "HTML, CSS, JavaScript, React, Node.js",
                    null, user, null, LocalDate.now(), null));

// Lưu vào DB
            jobRepository.saveAll(jobList);


        };
    }

}
