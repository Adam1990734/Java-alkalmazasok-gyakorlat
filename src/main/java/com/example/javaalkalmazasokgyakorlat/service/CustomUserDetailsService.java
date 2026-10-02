package com.example.javaalkalmazasokgyakorlat.service;
import com.example.javaalkalmazasokgyakorlat.model.user.User;
import com.example.javaalkalmazasokgyakorlat.model.user.UserMoreDetails;
import com.example.javaalkalmazasokgyakorlat.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserMoreDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new UserMoreDetails(user);
    }
}