package com.amos.silog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Configuration class to enable JPA auditing.
 * This allows automatic population of createdAt, updatedAt, createdBy, and updatedBy fields
 * in entities that extend BaseEntity.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    /**
     * Bean that provides the current auditor (user) for JPA auditing.
     * In a real application, this would typically get the current authenticated user.
     * For simplicity, this returns a default value.
     * 
     * @return AuditorAware implementation that provides the current user
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        // In a real application, this would get the current authenticated user
        // For example: return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
        
        // For now, return a default value
        return () -> Optional.of("system");
    }
}