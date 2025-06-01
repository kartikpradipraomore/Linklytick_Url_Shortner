package com.linklytics.securityconfig;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {

    private String token;


}
