package com.linklytics.usersecurityconfig;

import com.linklytics.models.User;
import com.linklytics.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found With userName" + username));
        return UserDetailsImple.build(user);

    }
}
