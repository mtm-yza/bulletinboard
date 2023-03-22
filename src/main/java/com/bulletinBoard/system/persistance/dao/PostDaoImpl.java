package com.bulletinBoard.system.persistance.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    private static final String TABLE_NAME = "posts";

    /**
     * <h2>SELECT_STMT</h2>
     * <p>
     * Common SELECT Statement of Post Entity
     * </p>
     */
    private static final String SELECT_STMT = "SELECT * FROM " + TABLE_NAME;

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
     * <h2>getSession</h2>
     * <p>
     * Get Current Session
     * </p>
     *
     * @return Session
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * <h2>insert</h2>
     * <p>
     * Insert Post
     * </p>
     * 
     * @param post PostForm
     */
    @Override
    public void insert(PostForm post) {

        String stmt = new StringBuilder("INSERT INTO " + TABLE_NAME).append(" (title, description, status) VALUES")
                .append(" (:title, :description, :status)").toString();

        Query<?> query = getSession().createSQLQuery(stmt);
        bindToQuery(post, query);
        query.executeUpdate();
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
    public void update(PostForm post) {

        String stmt = new StringBuilder("UPDATE " + TABLE_NAME + " SET ")
                .append("title= :title, description = :description, status = :status ").append("WHERE id=:id")
                .toString();

        Query<?> query = getSession().createSQLQuery(stmt);
        query.setParameter("id", post.getId());
        bindToQuery(post, query);
        query.executeUpdate();
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
        String stmt = "DELETE FROM " + TABLE_NAME + " WHERE id=:id";

        Query<?> query = getSession().createSQLQuery(stmt);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    /**
     * <h2>getAll</h2>
     * <p>
     * Get All Posts
     * </p>
     * 
     * @return id int
     */
    @Override
    public List<Post> getAll() {
        String stmt = new StringBuilder(SELECT_STMT).append(" ORDER BY id").toString();
        return getSession().createNativeQuery(stmt, Post.class).list();
    }

    /**
     * <h2>getByActiveStatus</h2>
     * <p>
     * Get Active Posts
     * </p>
     * 
     * @return List<Post>
     */
    @Override
    public List<Post> getByActiveStatus() {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE status = 1 ORDER BY id").toString();
        return getSession().createNativeQuery(stmt, Post.class).list();
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
    public List<Post> getByTitle(String title) {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE title = :title ORDER BY id").toString();
        return getSession().createNativeQuery(stmt, Post.class).setParameter("title", title).list();
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
    public int getCount() {
        return (int) getSession().createSQLQuery(COUNT_STMT).uniqueResult();
    }

    /**
     * <h2>bindToQuery</h2>
     * <p>
     * Convert PostForm to Entity And Bind To Query
     * </p>
     *
     * @param post  PostForm
     * @param query Query<?>
     */
    private void bindToQuery(PostForm post, Query<?> query) {
        query.setParameter("title", post.getTitle());
        query.setParameter("description", post.getDescription());
        query.setParameter("status", post.getStatus());
    }
}
