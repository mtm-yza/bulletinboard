package com.bulletinBoard.system.bl.service.post;

import java.util.List;

import org.springframework.data.domain.Page;

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
public interface PostService2 {

    /**
     * <h2>doAddPost</h2>
     * <p>
     * Add Post
     * </p>
     *
     * @param post PostForm
     *
     * @return boolean
     */
    public boolean doAddPost(PostForm post);

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * Get A List Of PostDTO
     * </p>
     *
     * @param page int
     * @param size int
     *
     * @return Page<PostDTO>
     */
    public Page<PostDTO> doGetPostList(int page, int size);

    /**
     * <h2>doGetPostListByActiveStatus</h2>
     * <p>
     * Get A List Of Post By Active Status
     * </p>
     *
     * @return Page<PostDTO>
     */
    public Page<PostDTO> doGetActivePosts(int page, int size);

    /**
     * <h2>doGetPublicPosts</h2>
     * <p>
     * Get Public Posts including User's Private Posts
     * </p>
     *
     * @param page  int
     * @param size  int
     * @param email String
     *
     * @return Page<PostDTO>
     */
    public Page<PostDTO> doGetPublicPosts(int page, int size, String email);

    /**
     * <h2>doGetPublicPostsByTitleAndAuthorName</h2>
     * <p>
     * Get Public Posts By Title And Author Name (Name or Email)
     * </p>
     *
     * @param page       int
     * @param size       int
     * @param postTitle  String
     * @param authorName String
     *
     * @return Page<PostDTO>
     */
    public Page<PostDTO> doGetPublicPostsByTitleAndAuthorName(int page, int size, String postTitle, String authorName);

    /**
     * <h2>doGetUserPosts</h2>
     * <p>
     * Get User's Post
     * </p>
     *
     * @param page  int
     * @param size  int
     * @param email String
     *
     * @return Page<PostDTO>
     */
    public Page<PostDTO> doGetUserPosts(int page, int size, String email);

    /**
     * <h2>doGetPostById</h2>
     * <p>
     * Get Post by ID 
     * </p>
     *
     * @param id int
     * 
     * @return PostDTO
     */
    public PostDTO doGetPostById(int id);

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
     * <h2>doUpdateImages</h2>
     * <p>
     * Update Images
     * </p>
     *
     * @param id     int
     * @param images List<String>
     *
     * @return void
     */
    public void doUpdateImages(int id, List<String> images);

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
