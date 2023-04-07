package com.bulletinBoard.system.persistance.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.persistance.dao.UserDao;
import com.bulletinBoard.system.persistance.entity.User;
import com.bulletinBoard.system.web.form.UserForm;

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
     * @param user UserForm
     */
    @Override
    public void dbInsertUser(UserForm user) {
        this.getSession().save(new User(user));
    }

    /**
     * <h2>dbGetUsers</h2>
     * <p>
     * Get All Users
     * </p>
     * 
     * @param offset int
     * @param limit  int
     * @return List<UserDTO>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UserDTO> dbGetUsers(int offset, int limit) {
        return getUserDtos(
                this.getSession().createQuery(SELECT_STMT).setFirstResult(offset).setMaxResults(limit).list());
    }

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     * 
     * @param id int
     * @return UserDTO
     */
    @Override
    public UserDTO dbGetUserById(int id) {
        User user = this.getSession().get(User.class, id);
        return new UserDTO(user);
    }

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get
     * </p>
     * 
     * @param email String
     * @return UserDTO
     */
    @SuppressWarnings("unchecked")
    @Override
    public UserDTO dbGetUserByEmail(String email) {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE email=:email").toString();
        List<User> user =  this.getSession().createQuery(stmt).setParameter("email", email).list();            
        return (!user.isEmpty()) ? new UserDTO(user.get(0)) : null;
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
     * @param user UserForm
     */
    @Override
    public void dbUpdateUser(UserForm user) {
        this.getSession().update(new User(user));
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

    /**
     * <h2>getUserDtos</h2>
     * <p>
     * Convert User Entity List to User DTO List
     * </p>
     *
     * @param list Lists<User>
     * @return List<UserDTO>
     */
    private List<UserDTO> getUserDtos(List<User> list) {
        return list.stream().map(item -> (item != null) ? new UserDTO(item) : null).collect(Collectors.toList());
    }
}
