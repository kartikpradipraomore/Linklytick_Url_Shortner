package com.linklytics.service;

import com.linklytics.DTOS.LoginRequest;
import com.linklytics.mapper.UrlMappingToDto;
import com.linklytics.models.User;
import com.linklytics.securityconfig.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    public User registerUser(User user);
    public JwtAuthenticationResponse login(LoginRequest request);

    User findByUserName(String name);

}
