package com.zdybel.course.dto.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}