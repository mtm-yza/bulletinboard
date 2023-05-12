package com.bulletinBoard.system.bl.service.post.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.bl.service.post.PostService;
import com.bulletinBoard.system.persistance.dao.post.PostDao;
import com.bulletinBoard.system.persistance.dao.user.UserDao;
import com.bulletinBoard.system.persistance.entity.Post;
import com.bulletinBoard.system.web.form.PostForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for PostServiceImpl
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Service
public class PostServiceImpl implements PostService {

    /**
     * <h2>postDao</h2>
     * <p>
     * Data Access Object of Post
     * </p>
     */
    @Autowired
    private PostDao postDao;

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>doAddPost</h2>
     * <p>
     * Add Post
     * </p>
     * 
     * @param post PostForm
     * @return boolean
     */
    @Override
    public boolean doAddPost(PostForm post) {
        List<Post> list = this.postDao.dbGetPostsByTitle(post.getTitle());
        if ((!list.isEmpty())) {
            return false;
        }
        Post postEntity = new Post(post);
        postEntity.setUser(this.userDao.dbGetUserByEmail(post.getUserEmail()));
        this.postDao.dbInsertPost(postEntity);
        return true;
    }

    /**
     * <h2>doGetPostList</h2>
     * <p>
     * Get A List Of All Posts
     * </p>
     * 
     * @param offset int
     * @param size   int
     * @return List<PostDTO>
     */
    @Override
    public List<PostDTO> doGetPostList(int offset, int size) {
        return this.getPostDto(this.postDao.dbGetPosts(offset, size));
    }

    /**
     * <h2>doGetPublicPosts</h2>
     * <p>
     * Get Public Posts
     * </p>
     * 
     * @param offset int
     * @param size   int
     * @param email  String
     * @return
     */
    @Override
    public List<PostDTO> doGetPublicPosts(int offset, int size, String email) {
        int userId = this.userDao.dbGetUserByEmail(email).getId();
        return this.getPostDto(this.postDao.dbGetUserPosts(offset, size, userId, true));
    }

    /**
     * <h2>doGetPublicPostsByTitleAndAuthorName</h2>
     * <p>
     * Get Public Posts by Title and Author's Name
     * </p>
     * 
     * @param offset     int
     * @param size       int
     * @param postTitle  String
     * @param authorName String
     * @return List<PostDTO>
     */
    @Override
    public List<PostDTO> doGetPublicPostsByTitleAndAuthorName(int offset, int size, String postTitle,
            String authorName) {
        return this.getPostDto(this.postDao.dbGetPublicPostsByTitleAndAuthorName(offset, size, postTitle, authorName));
    }

    /**
     * <h2>doGetUserPosts</h2>
     * <p>
     * Get User Posts
     * </p>
     * 
     * @param offset int
     * @param size   int
     * @param email  String
     * @return List<PostDTO>
     */
    @Override
    public List<PostDTO> doGetUserPosts(int offset, int size, String email) {
        int userId = this.userDao.dbGetUserByEmail(email).getId();
        return this.getPostDto(this.postDao.dbGetUserPosts(offset, size, userId, false));
    }

    /**
     * <h2>doGetActivePosts</h2>
     * <p>
     * Get Active Posts
     * </p>
     * 
     * @param offset int
     * @param size   int
     * @return
     */
    @Override
    public List<PostDTO> doGetActivePosts(int offset, int size) {
        return this.getPostDto(this.postDao.dbGetPostsByActiveStatus(offset, size));
    }

    /**
     * <h2>doGetPostCount</h2>
     * <p>
     * Get Total Number of Posts
     * </p>
     * 
     * @return int
     */
    @Override
    public int doGetPostCount() {
        return this.postDao.dbGetPostCount();
    }

    /**
     * <h2>doGetPublicPostCount</h2>
     * <p>
     * Get Total Number of Public Posts
     * </p>
     * 
     * @param email String
     * @return int
     */
    @Override
    public int doGetPublicPostCount(String email) {
        int userId = this.userDao.dbGetUserByEmail(email).getId();
        return this.postDao.dbGetUserPostCount(userId, true);
    }

    /**
     * <h2>doGetPublicPostCountByTitleAndAuthorName</h2>
     * <p>
     * Get Total Count of Public Post by Title and Author Name
     * </p>
     * 
     * @param postTitle  String
     * @param authorName String
     * @return int
     */
    @Override
    public int doGetPublicPostCountByTitleAndAuthorName(String postTitle, String authorName) {
        return this.postDao.dbGetPublicPostsByTitleAndAuthorName(postTitle, authorName);
    }

    /**
     * <h2>doGetUserPostCount</h2>
     * <p>
     * Get Total Number of User Posts
     * </p>
     * 
     * @param email String
     * @return int
     */
    @Override
    public int doGetUserPostCount(String email) {
        int userId = this.userDao.dbGetUserByEmail(email).getId();
        return this.postDao.dbGetUserPostCount(userId, false);
    }

    /**
     * <h2>doGetActivePostCount</h2>
     * <p>
     * Get Total Number of Active Posts
     * </p>
     * 
     * @return int
     */
    @Override
    public int doGetActivePostCount() {
        return this.postDao.dbGetPostCountByActiveStatus();
    }

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     * 
     * @param post PostForm
     */
    @Override
    public void doUpdatePost(PostForm postForm) {
        this.postDao.dbUpdatePost(new Post(postForm));
    }

    /**
     * <h2>doEnableDisablePost</h2>
     * <p>
     * Enable or Disable Post
     * </p>
     *
     * @param postForm PostForm
     */
    @Override
    public void doEnableDisablePost(PostForm postForm) {
        Post post = new Post(postForm);
        post.setIsActive(!post.getIsActive());
        this.postDao.dbUpdatePost(post);
    }

    /**
     * <h2>doDeletePostById</h2>
     * <p>
     * Delete Post By ID
     * </p>
     * 
     * @param id int
     */
    @Override
    public void doDeletePostById(int id) {
        this.postDao.dbDeletePost(id);
    }

    /**
     * <h2>getPostDto</h2>
     * <p>
     * Convert And Get A List Of Post from Post Entity List
     * </p>
     *
     * @param postList List<Post>
     * @return List<PostDTO>
     */
    private List<PostDTO> getPostDto(List<Post> postList) {
        if (postList == null) {
            return null;
        }
        return postList.stream().map(item -> new PostDTO(item)).collect(Collectors.toList());
    }
}
