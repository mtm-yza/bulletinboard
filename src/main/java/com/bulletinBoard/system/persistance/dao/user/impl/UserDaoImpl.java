package com.bulletinBoard.system.persistance.dao.user.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.persistance.dao.user.UserDao;
import com.bulletinBoard.system.persistance.entity.User;

/**
 * <h2>UserDaoImpl Class</h2>
 * <p>
 * Implementation Class For UserDao
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    /**
     * <h2>TABLE_NAME</h2>
     * <p>
     * User Table Name
     * </p>
     */
    private static final String TABLE_NAME = "User";
    /**
     * <h2>SELECT_STMT</h2>
     * <p>
     * Common SELECT Statement of User
     * </p>
     */
    private static final String SELECT_STMT = " FROM " + TABLE_NAME;
    /**
     * <h2>COUNT_STMT</h2>
     * <p>
     * Common SELECT Statement of User Count
     * </p>
     */
    private static final String COUNT_STMT = "SELECT Count(id) FROM " + TABLE_NAME;

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * Session Factory
     * </p>
     */
    @Autowired
    SessionFactory sessionFactory;

    /**
     * <h2>dbInsertUser</h2>
     * <p>
     * Insert User
     * </p>
     * 
     * @param user User
     */
    @Override
    public void dbInsertUser(User user) {
        this.getSession().save(user);
    }

    /**
     * <h2>dbGetUsers</h2>
     * <p>
     * Get All Users
     * </p>
     * 
     * @param offset int
     * @param limit  int
     * @return List<User>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> dbGetUsers(int offset, int limit) {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        stmt.append(" ORDER BY created_at");
        return this.getSession().createQuery(stmt.toString()).setFirstResult(offset).setMaxResults(limit).list();
    }

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     * 
     * @param id int
     * @return User
     */
    @Override
    public User dbGetUserById(int id) {
        return this.getSession().get(User.class, id);
    }

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get User by Email
     * </p>
     * 
     * @param email    String
     * @param password String
     * @return User
     */
    @SuppressWarnings("unchecked")
    @Override
    public User dbGetUserByEmail(String email) {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        stmt.append(" WHERE email= :email");
        List<User> user = this.getSession().createQuery(stmt.toString()).setParameter("email", email).list();
        return (!user.isEmpty()) ? user.get(0) : null;
    }

    /**
     * <h2>dbGetUserByCredential</h2>
     * <p>
     * Get User by Credential
     * </p>
     * 
     * @param email    String
     * @param password String
     * @return User
     */
    @SuppressWarnings("unchecked")
    @Override
    public User dbGetUserByCredential(String email, String password) {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        stmt.append(" WHERE email = :email");
        stmt.append(" AND password = :password");
        List<User> user = this.getSession().createQuery(stmt.toString()).setParameter("email", email)
                .setParameter("password", password).list();
        return (!user.isEmpty()) ? user.get(0) : null;
    }

    /**
     * <h2>dbGetUserCount</h2>
     * <p>
     * Get User Count
     * </p>
     * 
     * @return int
     */
    @Override
    public int dbGetUserCount() {
        Long count = (Long) this.getSession().createQuery(COUNT_STMT).uniqueResult();
        return count.intValue();
    }

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     * 
     * @param user User
     */
    @Override
    public void dbUpdateUser(User user) {
        User oldUser = this.dbGetUserById(user.getId());
        if (user.getAuthority() == null) {
            user.setAuthority(oldUser.getAuthority());
        }
        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        this.getSession().merge(user);
    }

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     * 
     * @param id int
     */
    @Override
    public void dbDeleteUser(int id) {
        User user = this.getSession().get(User.class, id);
        this.getSession().delete(user);
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
        return sessionFactory.getCurrentSession();
    }
}
