package com.bulletinBoard.system.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.persistance.entity.User;

/**
 * <h2>UserRepository Class</h2>
 * <p>
 * Process for Displaying UserRepository
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     *
     * @param email String
     * @return Optional<User>
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public Optional<User> dbGetUserByEmail(@Param("email") String email);

    /**
     * <h2>dbGetUserByCredential</h2>
     * <p>
     * Get User by Credential
     * </p>
     *
     * @param email    String
     * @param password String
     * @return Optional<User>
     */
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    public Optional<User> dbGetUserByCredential(@Param("email") String email, @Param("password") String password);
}
