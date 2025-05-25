package com.job_freelance_internal_db.object;

import jakarta.persistence.*;

@Entity
@Table(name = "cv")
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String desiredPosition;

    @Column(length = 1000)
    private String skills;

    @Lob
    private byte[] fileData;

    private String fileName;
    private String fileType;

    // 🔗 Liên kết với User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_cv;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    public CV(String fullName, String email, String phone, String desiredPosition, String skills, byte[] fileData, String fileName, String fileType) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.desiredPosition = desiredPosition;
        this.skills = skills;
        this.fileData = fileData;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public CV() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user_cv;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public String getSkills() {
        return skills;
    }

    public String getDesiredPosition() {
        return desiredPosition;
    }

    public String getPhone() {
        return phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setUser(User user) {
        this.user_cv = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
