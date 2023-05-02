package com.bulletinBoard.system.bl.service.post;

import java.util.List;

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.web.form.PostForm;

/**
 * <h2>PostService Class</h2>
 * <p>
 * Process for PostService
 * </p>
 * 
 * @author YeZawAung
 *
 */
public interface PostService {

    /**
     * <h2>doAddPost</h2>
     * <p>
     * Add Post
     * </p>
     *
     * @param post PostForm
     * @return boolean
     */
    public boolean doAddPost(PostForm post);

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * Get A List Of PostDTO
     * </p>
     *
     * @param offset int
     * @param size   int
     * @return List<PostDTO>
     */
    public List<PostDTO> doGetPostList(int offset, int size);

    /**
     * <h2>doGetPostListByActiveStatus</h2>
     * <p>
     * Get A List Of Post By Active Status
     * </p>
     *
     * @return List<PostDTO>
     */
    public List<PostDTO> doGetActivePosts(int offset, int size);

    /**
     * <h2>doGetUserPosts</h2>
     * <p>
     * Get User's Post
     * </p>
     *
     * @param offset int
     * @param size   int
     * @param email  String
     * @return List<PostDTO>
     */
    public List<PostDTO> doGetUserPosts(int offset, int size, String email);

    /**
     * <h2>doGetPostCount</h2>
     * <p>
     * Get Total Number Of Posts
     * </p>
     *
     * @return int
     */
    public int doGetPostCount();

    /**
     * <h2>doGetPostCount</h2>
     * <p>
     * Get Total Number of Active Posts
     * </p>
     *
     * @return int
     */
    public int doGetActivePostCount();

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param post PostForm
     */
    public void doUpdatePost(PostForm post);

    /**
     * <h2>doEnableDisablePost</h2>
     * <p>
     * Enable or Disable Post
     * </p>
     *
     * @param postForm PostForm
     */
    public void doEnableDisablePost(PostForm postForm);

    /**
     * <h2>doDeletePost</h2>
     * <p>
     * Delete Post By ID
     * </p>
     *
     * @param id int
     */
    public void doDeletePostById(int id);
}
