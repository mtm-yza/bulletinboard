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

@Repository
@Transactional
public class PostDaoImpl implements PostDao {

    private static final String TABLE_NAME = "posts";
    private static final String SELECT_STMT = "SELECT * FROM " + TABLE_NAME;
    private static final String COUNT_STMT = "SELECT Count(id) FROM " + TABLE_NAME;

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void insert(PostForm post) {

        String stmt = new StringBuilder("INSERT INTO " + TABLE_NAME).append(" (title, description, status) VALUES")
                .append(" (:title, :description, :status)").toString();

        Query<?> query = getSession().createSQLQuery(stmt);
        bindToQuery(post, query);
        query.executeUpdate();
    }

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

    @Override
    public void delete(int id) {
        String stmt = "DELETE FROM " + TABLE_NAME + " WHERE id=:id";

        Query<?> query = getSession().createSQLQuery(stmt);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Post> getAll() {
        String stmt = new StringBuilder(SELECT_STMT).append(" ORDER BY id").toString();
        return getSession().createNativeQuery(stmt, Post.class).list();
    }

    @Override
    public List<Post> getByActiveStatus() {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE status = 1 ORDER BY id").toString();
        return getSession().createNativeQuery(stmt, Post.class).list();
    }

    @Override
    public int getCount() {
        return (int) getSession().createSQLQuery(COUNT_STMT).uniqueResult();
    }

    private void bindToQuery(PostForm post, Query query) {
        query.setParameter("title", post.getTitle());
        query.setParameter("description", post.getDescription());
        query.setParameter("status", post.getStatus());
    }
}
