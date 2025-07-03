package com.amos.silog.auth.service;

import com.amos.silog.auth.dto.RegisterRequestDto;
import com.amos.silog.auth.model.AppUser;
import com.amos.silog.auth.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public String register(RegisterRequestDto request) {
        log.info("Registering user {}", request.getEmail());
        if (appUserRepository.existsByEmail(request.getEmail()))
            throw new IllegalArgumentException("Email already exists");
        AppUser user = new AppUser()
                .withEmail(request.getEmail())
                .withName(request.getName())
                .withPasswordHash(request.getPassword())
                .withRole(request.getRole());
        appUserRepository.save(user);
        return ("User registered successfully with email: " + request.getEmail());
    }


    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with  email " + username + "  is not found"));
    }


}
