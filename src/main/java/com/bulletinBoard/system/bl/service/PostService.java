package com.bulletinBoard.system.bl.service;

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
     * <h2>add</h2>
     * <p>
     * Add Post Form
     * </p>
     *
     * @param post PostForm
     * @return boolean
     */
    public boolean add(PostForm post);

    /**
     * <h2>update</h2>
     * <p>
     * Add Post Form
     * </p>
     *
     * @param post PostForm
     * @param flag int
     * @return void
     */
    public void update(PostForm post, int flag);

    /**
     * <h2>delete</h2>
     * <p>
     * Delete Post By ID
     * </p>
     *
     * @param id int
     * @return void
     */
    public void delete(int id);

    /**
     * <h2>getAll</h2>
     * <p>
     * Get A List Of PostDTO
     * </p>
     *
     * @param offset int
     * @param size   int
     * @return List<PostDTO>
     */
    public List<PostDTO> getAll(int offset, int size);

    /**
     * <h2>getByStatusActive</h2>
     * <p>
     * Get A List Of Post By Active Status
     * </p>
     *
     * @return List<PostDTO>
     */
    public List<PostDTO> getByStatusActive();

    /**
     * <h2>getCount</h2>
     * <p>
     * Get Total Number Of Posts
     * </p>
     *
     * @return int
     */
    public int getCount();
}
