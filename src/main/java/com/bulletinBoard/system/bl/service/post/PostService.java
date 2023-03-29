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
     * <h2>doUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param post PostForm
     * @param flag int
     * @return void
     */
    public void doUpdatePost(PostForm post, int flag);

    /**
     * <h2>doDeletePost</h2>
     * <p>
     * Delete Post By ID
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doDeletePostById(int id);

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
    public List<PostDTO> doGetPostListByActiveStatus();

    /**
     * <h2>doGetPostCount</h2>
     * <p>
     * Get Total Number Of Posts
     * </p>
     *
     * @return int
     */
    public int doGetPostCount();
}
