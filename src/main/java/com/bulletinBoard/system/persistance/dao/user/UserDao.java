package com.bulletinBoard.system.persistance.dao.user;

import java.util.List;

import com.bulletinBoard.system.persistance.entity.User;


/**
 * <h2>UserDao Class</h2>
 * <p>
 * Process for Displaying UserDao
 * </p>
 * 
 * @author YeZawAung
 *
 */
public interface UserDao {
    /**
     * <h2>dbInsertUser</h2>
     * <p>
     * Insert User
     * </p>
     *
     * @param user User
     */
    public void dbInsertUser(User user);

    /**
     * <h2>dbGetUsers</h2>
     * <p>
     * Get Users
     * </p>
     *
     * @param offset
     * @param limit
     * @return List<User>
     */
    public List<User> dbGetUsers(int offset, int limit);

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * 
     * </p>
     *
     * @param id int
     * @return User
     */
    public User dbGetUserById(int id);

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get User By Email 
     * </p>
     *
     * @param email String
     * @return User
     */
    public User dbGetUserByEmail(String email);
    
    public User dbGetUserByCredential(String email, String password);

    /**
     * <h2>dbGetUserCount</h2>
     * <p>
     * Get User Count
     * </p>
     *
     * @return int
     */
    public int dbGetUserCount();

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param user User
     */
    public void dbUpdateUser(User user);

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id int
     */
    public void dbDeleteUser(int id);
}
