package com.amos.silog.auth.model;

import com.amos.silog.issue.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entity representing a user in the system.
 * This class extends BaseEntity to inherit common fields like id, createdAt, updatedAt, etc.
 */
@Entity
@Table(name = "app_users")
@Getter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userCache")
@SQLDelete(sql = "UPDATE app_users SET deleted = true WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class AppUser extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, name = "password_hash")
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    public AppUser withName(String name) {
        this.name = name;
        return this;
    }

    public AppUser withEmail(String email) {
        this.email = email;
        return this;
    }

    public AppUser withPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public AppUser withRole(UserRole role) {
        this.role = role;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}