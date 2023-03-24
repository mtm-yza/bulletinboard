package com.bulletinBoard.system.persistance.dao;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.persistance.entity.User;
import com.bulletinBoard.system.web.form.UserForm;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private static final String TABLE_NAME = "users";
    private static final String SELECT_STMT = "SELECT * FROM " + TABLE_NAME;
    private static final String COUNT_STMT = "SELECT Count(id) FROM " + TABLE_NAME;

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void insert(UserForm user) {
        String stmt = new StringBuilder("INSERT INTO " + TABLE_NAME).append(" (name, email, address, password) VALUES")
                .append(" (:name, :email, :address, :password)").toString();
        Query<?> query = getSession().createSQLQuery(stmt);
        bindToQuery(user, query);
        query.setParameter("password", user.getPassword());
        query.executeUpdate();
    }


    @Override
    public void update(UserForm user) {
        String stmt = new StringBuilder("UPDATE " + TABLE_NAME + " SET ")
                .append("name = :name, email = :email, address= :address").append(" WHERE id=:id")
                .toString();
        Query<?> query = getSession().createSQLQuery(stmt);
        bindToQuery(user, query);
        query.setParameter("id", user.getId());
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
    public User getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public User getByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAll(int offset, int limit) {
        String stmt = new StringBuilder(SELECT_STMT).append(" ORDER BY id").append(" LIMIT " + limit)
                .append(" OFFSET " + offset).toString();
        return getSession().createNativeQuery(stmt, User.class).list();
    }
  
    @Override
    public int getCount() {
        BigInteger count = (BigInteger) getSession().createSQLQuery(COUNT_STMT).uniqueResult();
        return count.intValue();
    }
    
    private void bindToQuery(UserForm user, Query<?> query) {
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("address", user.getAddress());
    }
}
