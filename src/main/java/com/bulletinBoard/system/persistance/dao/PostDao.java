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
     * <h2>dbInsertPost</h2>
     * <p>
     * Insert Post
     * </p>
     *
     * @param post PostForm
     */
    public void dbInsertPost(PostForm post);

    /**
     * <h2>dbUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param post PostForm
     */
    public void dbUpdatePost(PostForm post);

    /**
     * <h2>dbDeletePost</h2>
     * <p>
     * Delete Post By ID
     * </p>
     *
     * @param id int
     */
    public void dbDeletePost(int id);

    /**
     * <h2>dbGetPosts</h2>
     * <p>
     * Get All Post
     * </p>
     *
     * @param offset int
     * @param limit  int
     * @return List<Post>
     */
    public List<Post> dbGetPosts(int offset, int limit);

    /**
     * <h2>dbGetPostByActiveStatus</h2>
     * <p>
     * Get Active Posts
     * </p>
     *
     * @return List<Post>
     */
    public List<Post> dbGetPostByActiveStatus();

    /**
     * <h2>getPostsByTitle</h2>
     * <p>
     * Get Posts By Title
     * </p>
     *
     * @param title String
     * @return List<Post>
     */
    public List<Post> dbPostsByTitle(String title);

    /**
     * <h2>getCount</h2>
     * <p>
     * Get Total Number of Post
     * </p>
     *
     * @return int
     */
    public int dbGetPostCount();
}
