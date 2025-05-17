package com.job_freelance_internal_db.object.dto;

import com.job_freelance_internal_db.object.Role;
import com.job_freelance_internal_db.object.User;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterDTO {
    @NotNull(message = "Username is null")
    private String username;
    @NotNull(message = "Password is null") @Size(min = 8, message = "Password must have enough 8 character")
    private String password;
    @NotNull(message = "Re-password is null") @Size(min = 8, message = "Password must have enough 8 character")
    private String rePassword;

    @AssertTrue(message = "Password and Re-password mismatch")
    public boolean isValid() {
        return password != null && password.equals(rePassword);
    }

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setActive(true);
        return user;
    }
}
