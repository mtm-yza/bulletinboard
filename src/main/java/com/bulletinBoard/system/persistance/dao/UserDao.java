package com.bulletinBoard.system.persistance.dao;

import java.util.List;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.web.form.UserForm;

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
     * @param user UserForm
     */
    public void dbInsertUser(UserForm user);

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param user UserForm
     */
    public void dbUpdateUser(UserForm user);

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id int
     */
    public void dbDeleteUser(int id);

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * 
     * </p>
     *
     * @param id int
     * @return UserDTO
     */
    public UserDTO dbGetUserById(int id);

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get User By Email 
     * </p>
     *
     * @param email String
     * @return UserDTO
     */
    public UserDTO dbGetUserByEmail(String email);

    /**
     * <h2>dbGetUsers</h2>
     * <p>
     * Get Users
     * </p>
     *
     * @param offset
     * @param limit
     * @return List<UserDTO>
     */
    public List<UserDTO> dbGetUsers(int offset, int limit);

    /**
     * <h2>dbGetUserCount</h2>
     * <p>
     * Get User Count
     * </p>
     *
     * @return int
     */
    public int dbGetUserCount();
}
