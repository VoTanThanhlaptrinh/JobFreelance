package com.job_freelance_internal_db.service;

import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.object.User;

public interface UserService {
    public Response checkUser(String username, String password);

    public Response register(User user);
}
