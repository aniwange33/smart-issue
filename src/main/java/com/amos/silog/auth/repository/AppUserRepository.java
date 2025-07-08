package com.amos.silog.auth.repository;

import com.amos.silog.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    /**
     * Find a user by email address
     *
     * @param email the email address to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<AppUser> findByEmail(String email);



    /**
     * Check if a user with the given email exists
     *
     * @param email the email address to check
     * @return true if a user with the email exists, false otherwise
     */
    boolean existsByEmail(String email);

}