package com.bulletinBoard.system.persistance.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.common.util.HibernateUtil;
import com.bulletinBoard.system.persistance.entity.Post;
import com.bulletinBoard.system.web.form.PostForm;

@Repository
public class PostDaoImpl implements PostDao {

    private static final String TABLE_NAME = "posts";
    private static final String SELECT_STMT = "SELECT * FROM " + TABLE_NAME;
    private static final String COUNT_STMT = "SELECT Count(id) FROM " + TABLE_NAME;

    SessionFactory sessionFactory;

    public PostDaoImpl() {
        super();
        this.sessionFactory = HibernateUtil.getSessionFactory();
        this.sessionFactory.openSession();
    }

    @Override
    @Transactional
    public void insert(PostForm post) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String stmt = new StringBuilder("INSERT INTO " + TABLE_NAME).append(" (title, description, status) VALUES")
                .append(" (:title, :description, :status)").toString();

        Query<?> query = session.createSQLQuery(stmt);
        bindToQuery(post, query);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void update(PostForm post) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String stmt = new StringBuilder("UPDATE " + TABLE_NAME + " SET ")
                .append("title= :title, description = :description, status = :status ")
                .append("WHERE id=:id").toString();

        Query<?> query = session.createSQLQuery(stmt);
        query.setParameter("id", post.getId());
        bindToQuery(post, query);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        String stmt = "DELETE FROM " + TABLE_NAME + " WHERE id=:id";
        Transaction transaction = session.beginTransaction();
        
        Query<?> query = session.createSQLQuery(stmt);
        query.setParameter("id", id);
        query.executeUpdate();
        
        transaction.commit();
        session.close();
    }

    @Override
    public List<Post> getAll() {
        Session session = sessionFactory.openSession();
        List<Post> list = session.createNativeQuery(SELECT_STMT, Post.class).list();
        session.close();
        return list;
    }

    @Override
    public List<Post> getByActiveStatus() {
        Session session = sessionFactory.openSession();

        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE status = 1").toString();
        List<Post> list = session.createNativeQuery(stmt, Post.class).list();

        session.close();
        return list;
    }

    @Override
    public int getCount() {
        Session session = sessionFactory.openSession();
        int count = (int) session.createSQLQuery(COUNT_STMT).uniqueResult();
        session.close();
        return count;
    }

    private void bindToQuery(PostForm post, Query query) {
        query.setParameter("title", post.getTitle());
        query.setParameter("description", post.getDescription());
        query.setParameter("status", post.getStatus());
    }
}
