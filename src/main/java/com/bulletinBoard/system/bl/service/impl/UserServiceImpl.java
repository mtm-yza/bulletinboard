package com.bulletinBoard.system.bl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.bl.service.UserService;
import com.bulletinBoard.system.common.Constant;
import com.bulletinBoard.system.persistance.dao.UserDao;
import com.bulletinBoard.system.web.form.UserForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Implementation Class for User Service
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * <h2>userDao</h2>
     * <p>
     * Data Access Object for User
     * </p>
     */
    @Autowired
    UserDao userDao;

    /**
     * <h2>doAddUser</h2>
     * <p>
     * Add User
     * </p>
     * 
     * @param user UserForm
     * @return int
     */
    @Override
    public int doAddUser(UserForm user) {
        UserDTO userDto = doGetUserByEmail(user.getEmail());
        if (userDto != null) {
            return Constant.EMAIL_ALREADY_REGISTERED;
        }
        userDao.dbInsertUser(user);
        return Constant.SUCCESS;
    }

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
    @Override
    public List<UserDTO> doGetUserList(int offset, int size) {
        return userDao.dbGetUsers(offset, size);
    }

    /**
     * <h2>doGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     * 
     * @param id int
     * @return UserDTO
     */
    @Override
    public UserDTO doGetUserById(int id) {
        return userDao.dbGetUserById(id);
    }

    /**
     * <h2>doGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     * 
     * @param email String
     * @return UserDTO
     */
    @Override
    public UserDTO doGetUserByEmail(String email) {
        return userDao.dbGetUserByEmail(email);
    }

    /**
     * <h2>doGetUserCount</h2>
     * <p>
     * Get User Count
     * </p>
     * 
     * @return int
     */
    @Override
    public int doGetUserCount() {
        return userDao.dbGetUserCount();
    }

    /**
     * <h2>doUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     * 
     * @param user int
     * @param flag int
     * @return int
     */
    @Override
    public int doUpdateUser(UserForm user, int flag) {
        userDao.dbUpdateUser(user);
        return Constant.SUCCESS;
    }

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     * 
     */
    @Override
    public void doDeleteUser(int id) {
        userDao.dbDeleteUser(id);
    }
}
