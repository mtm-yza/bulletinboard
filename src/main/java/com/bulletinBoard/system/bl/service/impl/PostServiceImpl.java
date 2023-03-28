package com.bulletinBoard.system.bl.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.bl.service.PostService;
import com.bulletinBoard.system.persistance.dao.PostDao;
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
        List<Post> list = postDao.dbPostsByTitle(post.getTitle());
        if ((!list.isEmpty())) {
            return false;
        }
        postDao.dbInsertPost(post);
        return true;
    }

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     * 
     * @param post PostForm
     * @param flag int
     */
    @Override
    public void doUpdatePost(PostForm post, int flag) {
        if (flag == 1) {
            post.setStatus((post.getStatus() == 1) ? 0 : 1);
        }
        postDao.dbUpdatePost(post);
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
        postDao.dbDeletePost(id);
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
        return getPostDto(postDao.dbGetPosts(offset, size));
    }

    /**
     * <h2>doGetPostListByActiveStatus</h2>
     * <p>
     * Get A List of Post by Active Status
     * </p>
     * 
     * @return List<PostDTO>
     */
    @Override
    public List<PostDTO> doGetPostListByActiveStatus() {
        return getPostDto(postDao.dbGetPostByActiveStatus());
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
        return postDao.dbGetPostCount();
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
        return postList.stream()
                .map(item -> new PostDTO(item.getId(), item.getTitle(), item.getDescription(), item.getStatus()))
                .collect(Collectors.toList());
    }
}
