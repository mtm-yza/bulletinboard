package com.bulletinBoard.system.persistance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.persistance.entity.Post;

/**
 * <h2>PostRepository Class</h2>
 * <p>
 * Process for Displaying PostRepository
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    /**
     * <h2>dbGetPostsByActiveStatus</h2>
     * <p>
     * Get Post by Active Status
     * </p>
     *
     * @param pageable Pageable
     *
     * @return Page<Post>
     */
    @Query("SELECT p FROM Post p WHERE p.isActive = true")
    public Page<Post> dbGetPostsByActiveStatus(Pageable pageable);

    /**
     * <h2>dbGetPostsByTitle</h2>
     * <p>
     * Get Posts By Title
     * </p>
     *
     * @param title    String
     * @param pageable Pageable
     *
     * @return Page<Post>
     */
    @Query("SELECT p FROM Post p WHERE p.title = :title")
    public Page<Post> dbGetPostsByTitle(@Param("title") String title, Pageable pageable);

    /**
     * <h2>dbGetUserPosts</h2>
     * <p>
     * Get User Posts
     * </p>
     *
     * @param userId   int
     * @param pageable Pageable
     *
     * @return Page<Post>
     */
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    public Page<Post> dbGetUserPosts(@Param("userId") int userId, Pageable pageable);
    
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId OR p.isActive = true")
    public Page<Post> dbGetUserPublicPost(@Param("userId") int userId, Pageable pageable);

    /**
     * <h2>dbGetPublicPostsByTitleAndAuthorName</h2>
     * <p>
     * Get Public Posts By Title and Author Name
     * </p>
     *
     * @param title      String
     * @param authorName String
     * @param pageable   Pageable
     *
     * @return Page<Post>
     */
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:title% AND (p.user.name LIKE %:authorName% OR p.user.email LIKE %:authorName%) AND p.isActive = true")
    public Page<Post> dbGetPublicPostsByTitleAndAuthorName(@Param("title") String title,
            @Param("authorName") String authorName, Pageable pageable);
}
