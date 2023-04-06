package com.bulletinBoard.system.persistance.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    private static final String TABLE_NAME = "users";
    /**
     * <h2>SELECT_STMT</h2>
     * <p>
     * Common SELECT Statement of User
     * </p>
     */
    private static final String SELECT_STMT = "SELECT * FROM " + TABLE_NAME;
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
        String stmt = new StringBuilder("INSERT INTO " + TABLE_NAME).append(" (name, email, address, password) VALUES")
                .append(" (:name, :email, :address, :password)").toString();
        Query<?> query = getSession().createSQLQuery(stmt);
        bindToQuery(user, query);
        query.setParameter("password", user.getPassword());
        query.executeUpdate();
    }

    /**
     * <h2>dbGetUsers</h2>
     * <p>
     * Get All Users
     * </p>
     * 
     * @param offset int
     * @param limit int
     * @return List<UserDTO>
     */
    @Override
    public List<UserDTO> dbGetUsers(int offset, int limit) {
        String stmt = new StringBuilder(SELECT_STMT).append(" ORDER BY id").append(" LIMIT " + limit)
                .append(" OFFSET " + offset).toString();
        return getUserDtos(getSession().createNativeQuery(stmt, User.class).list());
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
        return null;
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
    @Override
    public UserDTO dbGetUserByEmail(String email) {
        String stmt = new StringBuilder(SELECT_STMT).append(" WHERE email=:email").toString();
        return getUserDto(getSession().createNativeQuery(stmt, User.class).setParameter("email", email).uniqueResult());
    }

    /**
     * <h2>dbGetUserCount</h2>
     * <p>
     * Get User Cou t
     * </p>
     * 
     * @return int
     */
    @Override
    public int dbGetUserCount() {
        BigInteger count = (BigInteger) getSession().createSQLQuery(COUNT_STMT).uniqueResult();
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
        String stmt = new StringBuilder("UPDATE " + TABLE_NAME + " SET ")
                .append("name = :name, email = :email, address= :address").append(" WHERE id=:id").toString();
        System.out.println(stmt);
        Query<?> query = getSession().createSQLQuery(stmt);
        bindToQuery(user, query);
        query.setParameter("id", user.getId());
        query.executeUpdate();
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
        String stmt = "DELETE FROM " + TABLE_NAME + " WHERE id=:id";
        Query<?> query = getSession().createSQLQuery(stmt);
        query.setParameter("id", id);
        query.executeUpdate();
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
     * <h2>bindToQuery</h2>
     * <p>
     * Bind User Information To Query
     * </p>
     *
     * @param user UserForm
     * @param query Query<?>
     */
    private void bindToQuery(UserForm user, Query<?> query) {
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("address", user.getAddress());
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
        return list.stream().map(item -> getUserDto(item)).collect(Collectors.toList());
    }

    /**
     * <h2>getUserDto</h2>
     * <p>
     * Get User Entity to User DTO
     * </p>
     *
     * @param user User
     * @return UserDTO
     */
    private UserDTO getUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAddress());
    }
}
