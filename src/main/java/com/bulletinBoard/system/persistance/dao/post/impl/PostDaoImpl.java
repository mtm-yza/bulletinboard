package com.bulletinBoard.system.persistance.dao.post.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.persistance.dao.post.PostDao;
import com.bulletinBoard.system.persistance.entity.Post;

/**
 * <h2>PostDaoImpl Class</h2>
 * <p>
 * Implementation Class of PostDao
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Repository
@Transactional
public class PostDaoImpl implements PostDao {

    /**
     * <h2>TABLE_NAME</h2>
     * <p>
     * Table Name of Post Entity
     * </p>
     */
    private static final String TABLE_NAME = "Post";

    /**
     * <h2>SELECT_STMT</h2>
     * <p>
     * Common SELECT Statement of Post Entity
     * </p>
     */
    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    /**
     * <h2>COUNT_STMT</h2>
     * <p>
     * Common Statement of count
     * </p>
     */
    private static final String COUNT_STMT = "SELECT Count(id) FROM " + TABLE_NAME;

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * SessionFactory To Create Session For Database
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>insert</h2>
     * <p>
     * Insert Post
     * </p>
     * 
     * @param post PostForm
     */
    @Override
    public void dbInsertPost(Post post) {
        this.getSession().save(post);
    }

    /**
     * <h2>getAll</h2>
     * <p>
     * Get All Posts
     * </p>
     * 
     * @param offset int
     * @param limit  int
     * @return List<Post>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> dbGetPosts(int offset, int limit) {
        String stmt = new StringBuilder(SELECT_STMT).append(" ORDER BY created_at DESC").toString();
        return this.getSession().createQuery(stmt).setFirstResult(offset).setMaxResults(limit).list();
    }

    /**
     * <h2>dbGetUserPosts</h2>
     * <p>
     * Get User's Posts
     * </p>
     * 
     * @param offset int
     * @param limit  int
     * @param userId int
     * @return List<Post>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> dbGetUserPosts(int offset, int limit, int userId) {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE user_Id=:userId ORDER BY created_at DESC").toString();
        return this.getSession().createQuery(stmt).setParameter("userId", userId).setFirstResult(offset)
                .setMaxResults(limit).list();
    }

    /**
     * <h2>getByTitle</h2>
     * <p>
     * Get Posts By Title
     * </p>
     *
     * @param title String
     * @return List<Post>
     */
    @SuppressWarnings("unchecked")
    public List<Post> dbGetPostsByTitle(String title) {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE title = :title ORDER BY id").toString();
        return this.getSession().createQuery(stmt).setParameter("title", title).list();
    }

    /**
     * <h2>getByActiveStatus</h2>
     * <p>
     * Get Active Posts
     * </p>
     * 
     * @return List<Post>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> dbGetPostByActiveStatus() {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE status = 1 ORDER BY id").toString();
        return this.getSession().createQuery(stmt).list();
    }

    /**
     * <h2>dbGetPostById</h2>
     * <p>
     * Get Post By ID
     * </p>
     *
     * @param id
     * @return Post
     */
    @Override
    public Post dbGetPostById(int id) {
        return this.getSession().get(Post.class, id);
    }

    /**
     * <h2>getCount</h2>
     * <p>
     * Get Number Of All Posts
     * </p>
     * 
     * @return int
     */
    @Override
    public int dbGetPostCount() {
        Long count = (Long) this.getSession().createQuery(COUNT_STMT).uniqueResult();
        return count.intValue();
    }

    /**
     * <h2>update</h2>
     * <p>
     * Update Post
     * </p>
     * 
     * @param post PostForm
     */
    @Override
    public void dbUpdatePost(Post post) {
        this.getSession().update(post);
    }

    /**
     * <h2>delete</h2>
     * <p>
     * Delete Post By ID
     * </p>
     * 
     */
    @Override
    public void dbDeletePost(int id) {
        Post post = this.dbGetPostById(id);
        this.getSession().delete(post);
    }

    /**
     * <h2>getSession</h2>
     * <p>
     * Get Current Session
     * </p>
     *
     * @return Session
     */
    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
