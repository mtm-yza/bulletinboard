package com.bulletinBoard.system.persistance.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.persistance.dao.PostDao;
import com.bulletinBoard.system.persistance.entity.Post;
import com.bulletinBoard.system.web.form.PostForm;

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
    SessionFactory sessionFactory;

    /**
     * <h2>insert</h2>
     * <p>
     * Insert Post
     * </p>
     * 
     * @param post PostForm
     */
    @Override
    public void dbInsertPost(PostForm post) {
        this.getSession().save(new Post(post));
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
    public void dbUpdatePost(PostForm post) {
        this.getSession().update(new Post(post));
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
        String stmt = new StringBuilder("DELETE FROM " + TABLE_NAME).append(" WHERE id=:id").toString();
        Query<?> query = this.getSession().createQuery(stmt);
        query.setParameter("id", id);
        query.executeUpdate();
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
        String stmt = new StringBuilder(SELECT_STMT).append(" ORDER BY id").toString();
        return this.getSession().createQuery(stmt).setMaxResults(limit).setFirstResult(offset).list();
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
     * <h2>getByTitle</h2>
     * <p>
     * Get Posts By Title
     * </p>
     *
     * @param title String
     * @return List<Post>
     */
    @SuppressWarnings("unchecked")
    public List<Post> dbPostsByTitle(String title) {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE title = :title ORDER BY id").toString();
        return this.getSession().createQuery(stmt).setParameter("title", title).list();
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
