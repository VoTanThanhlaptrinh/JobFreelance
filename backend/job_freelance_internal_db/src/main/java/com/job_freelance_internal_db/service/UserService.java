package com.job_freelance_internal_db.service;

import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.object.User;

public interface UserService {
    public Response<Object> checkUser(String username, String password);

    public Response<Object> register(User user);
}
