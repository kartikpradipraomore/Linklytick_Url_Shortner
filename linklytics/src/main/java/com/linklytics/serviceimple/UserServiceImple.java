package com.linklytics.serviceimple;

import com.linklytics.DTOS.LoginRequest;
import com.linklytics.mapper.UrlMappingToDto;
import com.linklytics.models.User;
import com.linklytics.repository.UserRepository;
import com.linklytics.securityconfig.JwtAuthenticationResponse;
import com.linklytics.securityconfig.JwtUtils;
import com.linklytics.service.UserService;
import com.linklytics.usersecurityconfig.UserDetailsImple;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImple implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;



    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword())  );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImple userDetailsImple = (UserDetailsImple) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetailsImple);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUserName(name).orElseThrow(
                ()-> new UsernameNotFoundException("User Not Found with User Name" + name)
        );
    }


}
