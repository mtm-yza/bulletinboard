package com.bulletinBoard.system.bl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.PostDTO;
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
     * <h2>add</h2>
     * <p>
     * Add PostForm
     * </p>
     * 
     * @param post PostForm
     * @return boolean 
     */
    @Override
    public boolean add(PostForm post) {
        List<Post> list = postDao.getByTitle(post.getTitle());
        if ((!list.isEmpty())) {
            return false;
        }
        postDao.insert(post);
        return true;
    }

    /**
     * <h2>update</h2>
     * <p>
     * Update PostForm
     * </p>
     * 
     * @param post PostForm
     * @param flag int
     */
    @Override
    public void update(PostForm post, int flag) {
        if (flag == 1) {
            post.setStatus((post.getStatus() == 1) ? 0 : 1);
        }
        postDao.update(post);
    }

    /**
     * <h2>delete</h2>
     * <p>
     * Delete Post By ID
     * </p>
     * 
     * @param id int
     */
    @Override
    public void delete(int id) {
        postDao.delete(id);
    }

    /**
     * <h2>getAll</h2>
     * <p>
     * Get A List Of All Posts
     * </p>
     * 
     * @return List<PostDTO>
     */
    @Override
    public List<PostDTO> getAll() {
        return getPostDto(postDao.getAll());
    }

    /**
     * <h2>getByStatusActive</h2>
     * <p>
     * Get A List of Post by Active Status
     * </p>
     * 
     * @return List<PostDTO>
     */
    @Override
    public List<PostDTO> getByStatusActive() {
        return getPostDto(postDao.getByActiveStatus());
    }

    /**
     * <h2>getCount</h2>
     * <p>
     * Get Total Number of Posts
     * </p>
     * 
     * @return int
     */
    @Override
    public int getCount() {
        return postDao.getCount();
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
