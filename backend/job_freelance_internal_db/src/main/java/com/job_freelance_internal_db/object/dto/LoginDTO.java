package com.job_freelance_internal_db.object.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class LoginDTO {
    @NotNull(message = "Username is null")
    private String username;
    @NotNull(message = "Password is null")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
