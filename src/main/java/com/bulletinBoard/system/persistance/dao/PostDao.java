package com.bulletinBoard.system.persistance.dao;

import java.util.List;

import com.bulletinBoard.system.persistance.entity.Post;
import com.bulletinBoard.system.web.form.PostForm;

/**
 * <h2>PostDao Class</h2>
 * <p>
 * Process for Displaying PostDao
 * </p>
 * 
 * @author YeZawAung
 *
 */
public interface PostDao {

    /**
     * <h2>insert</h2>
     * <p>
     * Insert Post
     * </p>
     *
     * @param post PostForm
     */
    public void insert(PostForm post);

    /**
     * <h2>update</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param post PostForm
     */
    public void update(PostForm post);

    /**
     * <h2>delete</h2>
     * <p>
     * Delete Post By ID
     * </p>
     *
     * @param id int
     */
    public void delete(int id);

    /**
     * <h2>getAll</h2>
     * <p>
     * Get All Post
     * </p>
     *
     * @return List<Post>
     */
    public List<Post> getAll();

    /**
     * <h2>getByActiveStatus</h2>
     * <p>
     * Get Active Posts
     * </p>
     *
     * @return List<Post>
     */
    public List<Post> getByActiveStatus();

    /**
     * <h2>getCount</h2>
     * <p>
     * Get Total Number of Post
     * </p>
     *
     * @return int
     */
    public int getCount();
}
