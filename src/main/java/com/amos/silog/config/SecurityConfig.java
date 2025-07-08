package com.amos.silog.config;

import com.amos.silog.auth.filter.JwtAuthenticationFilter;
import com.amos.silog.auth.filter.JwtRefreshFilter;
import com.amos.silog.auth.filter.JwtValidationFilter;
import com.amos.silog.auth.jwt.JwtAuthenticationProvider;
import com.amos.silog.auth.jwt.JwtService;
import com.amos.silog.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final AuthService userDetailsService;
    @Value("${app.cors.allowed-origins}")
    private   List<String> allowedOrigins;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager, JwtService jwtUtil) throws Exception {
        // Authentication filter responsible for login
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(jwtUtil, geAuthenticationManager());
        // Validation filter for checking JWT in every request
        JwtValidationFilter jwtValidationFilter = new JwtValidationFilter(authenticationManager);
        // refresh filter for checking JWT in every request
        JwtRefreshFilter jwtRefreshFilter = new JwtRefreshFilter(jwtUtil, authenticationManager);

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register",
                                "/swagger-ui.html","/v3/api-docs/**","/swagger-ui/**","/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> getCorsConfiguration()))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // generate token filter
                .addFilterAfter(jwtValidationFilter, JwtAuthenticationFilter.class) // validate token filter
                .addFilterAfter(jwtRefreshFilter, JwtValidationFilter.class); // refresh token filter
        return http.build();
    }

    private  CorsConfiguration getCorsConfiguration() {
       // log.info("Setting up CORS configuration origins {}", allowedOrigins);
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(allowedOrigins);
        config.addAllowedHeader("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("refreshToken");
        config.addAllowedMethod("*");
        return config;
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
