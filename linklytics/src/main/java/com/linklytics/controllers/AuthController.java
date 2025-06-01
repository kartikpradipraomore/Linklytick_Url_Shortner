package com.linklytics.controllers;

import com.linklytics.DTOS.LoginRequest;
import com.linklytics.DTOS.RegisterRequest;
import com.linklytics.models.User;
import com.linklytics.securityconfig.JwtAuthenticationResponse;
import com.linklytics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
   private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request){

        User user = new User();
        user.setUserName(request.getUserName());
        user.setRole("ROLE_USER");
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
}
