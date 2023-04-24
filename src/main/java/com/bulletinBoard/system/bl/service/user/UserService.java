package com.bulletinBoard.system.bl.service.user;

import java.util.List;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.web.form.UserForm;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Interface for User Service
 * </p>
 * 
 * @author YeZawAung
 *
 */
public interface UserService {

    /**
     * <h2>doAddUser</h2>
     * <p>
     * Add User
     * </p>
     *
     * @param post UserForm
     * @return int
     */
    public int doAddUser(UserForm post);

    /**
     * <h2>doGetUserList</h2>
     * <p>
     * Get User List
     * </p>
     *
     * @param offset int
     * @param size int
     * @return List<UserDTO>
     */
    public List<UserDTO> doGetUserList(int offset, int size);

    /**
     * <h2>doGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     *
     * @param id int
     * @return UserDTO
     */
    public UserDTO doGetUserById(int id);

    /**
     * <h2>doGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     *
     * @param email String
     * @return UserDTO
     */
    public UserDTO doGetUserByEmail(String email);

    /**
     * <h2>doGetUserCount</h2>
     * <p>
     * Get User Count
     * </p>
     *
     * @return int
     */
    public int doGetUserCount();

    /**
     * <h2>doUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param post UserForm
     * @return int
     */
    public int doUpdateUser(UserForm post);

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete User by ID
     * </p>
     *
     * @param id int
     */
    public void doDeleteUser(int id);
    
    public int doCheckUserCredential(String email, String password);
}
