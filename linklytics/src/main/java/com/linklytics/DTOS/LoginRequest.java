package com.linklytics.DTOS;


import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
