package com.amos.silog.config;

import com.amos.silog.auth.filter.JwtAuthenticationFilter;
import com.amos.silog.auth.filter.JwtRefreshFilter;
import com.amos.silog.auth.filter.JwtValidationFilter;
import com.amos.silog.auth.jwt.JwtAuthenticationProvider;
import com.amos.silog.auth.jwt.JwtService;
import com.amos.silog.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final AuthService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager, JwtService jwtUtil) throws Exception {
        // Authentication filter responsible for login
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(jwtUtil, geAuthenticationManager());
        // Validation filter for checking JWT in every request
        JwtValidationFilter jwtValidationFilter = new JwtValidationFilter(authenticationManager);
        // refresh filter for checking JWT in every request
        JwtRefreshFilter jwtRefreshFilter = new JwtRefreshFilter(jwtUtil, authenticationManager);

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // generate token filter
                .addFilterAfter(jwtValidationFilter, JwtAuthenticationFilter.class) // validate token filter
                .addFilterAfter(jwtRefreshFilter, JwtValidationFilter.class); // refresh token filter
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtService, userDetailsService);
    }


    @Bean
    public AuthenticationManager geAuthenticationManager() {
        return new ProviderManager(List.of(authenticationProvider(), jwtAuthenticationProvider()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
