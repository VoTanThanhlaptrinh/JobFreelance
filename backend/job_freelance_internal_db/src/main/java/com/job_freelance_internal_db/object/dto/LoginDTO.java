package com.job_freelance_internal_db.object.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    @NotNull(message = "Username is null")
    private String username;
    @NotNull(message = "Password is null")
    private String password;
}
