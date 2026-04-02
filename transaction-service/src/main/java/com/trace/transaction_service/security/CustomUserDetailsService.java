package com.trace.transaction_service.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) {
        return new User(
                email,
                "",
                List.of(new SimpleGrantedAuthority("ROLE_USER")) // ← yeh fix hai
        );
    }

}
