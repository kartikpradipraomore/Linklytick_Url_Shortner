package com.linklytics.DTOS;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {

    private String userName;
    private String email;
    private Set<String> roles;
    private String password;

}
