package com.job_freelance_internal_db.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    int status;
    T data;
    String message;
}
