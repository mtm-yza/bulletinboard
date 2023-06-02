package com.bulletinBoard.system.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bulletinBoard.system.persistance.entity.Authority;

/**
 * <h2>AuthorityRepository Class</h2>
 * <p>
 * Process for Displaying AuthorityRepository
 * </p>
 * 
 * @author YeZawAung
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
