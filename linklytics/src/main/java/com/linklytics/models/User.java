package com.linklytics.models;

import jakarta.persistence.*;
import lombok.*;

import java.security.PrivateKey;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String role = "ROLE_USER";
}
