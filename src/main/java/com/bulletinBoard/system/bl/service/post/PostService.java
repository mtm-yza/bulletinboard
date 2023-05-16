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
     * <h2>doGetPublicPosts</h2>
     * <p>
     * Get Public Posts including User's Private Posts
     * </p>
     *
     * @param offset int
     * @param size   int
     * @param email  String
     * @return List<PostDTO>
     */
    public List<PostDTO> doGetPublicPosts(int offset, int size, String email);

    /**
     * <h2>doGetPublicPostsByTitleAndAuthorName</h2>
     * <p>
     * Get Public Posts By Title And Author Name (Name or Email)
     * </p>
     *
     * @param offset     int
     * @param size       int
     * @param postTitle  String
     * @param authorName String
     * @return List<PostDTO>
     */
    public List<PostDTO> doGetPublicPostsByTitleAndAuthorName(int offset, int size, String postTitle,
            String authorName);

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
     * <h2>doGetPostById</h2>
     * <p>
     * Get Post By ID
     * </p>
     *
     * @param id int
     * @return PostDTO
     */
    public PostDTO doGetPostById(int id);

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
     * <h2>doGetPublicPostCount</h2>
     * <p>
     * Get Total Number of Public Post
     * </p>
     *
     * @return int
     */
    public int doGetPublicPostCount(String email);

    /**
     * <h2>doGetPublicPostCountByTitleAndAuthorName</h2>
     * <p>
     * Get Total Number of Public Post by Title and Author's Name
     * </p>
     *
     * @param postTitle  String
     * @param authorName String
     * @return int
     */
    public int doGetPublicPostCountByTitleAndAuthorName(String postTitle, String authorName);

    /**
     * <h2>doGetUserPostCount</h2>
     * <p>
     * Get Total Number of User Post
     * </p>
     *
     * @return int
     */
    public int doGetUserPostCount(String email);

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
