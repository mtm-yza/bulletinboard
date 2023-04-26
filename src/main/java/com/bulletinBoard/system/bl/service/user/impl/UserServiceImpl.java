package com.bulletinBoard.system.bl.service.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.bl.service.user.UserService;
import com.bulletinBoard.system.common.Constant;
import com.bulletinBoard.system.persistance.dao.user.UserDao;
import com.bulletinBoard.system.persistance.entity.User;
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
public class UserServiceImpl implements UserService, UserDetailsService {

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
        // Check if the Email is Already Registered.
        User userEntity = userDao.dbGetUserByEmail(user.getEmail());
        if (userEntity != null) {
            return Constant.EMAIL_ALREADY_REGISTERED;
        }
        userDao.dbInsertUser(new User(user));
        return Constant.SUCCESS;
    }

    /**
     * <h2>doGetUserList</h2>
     * <p>
     * Get User List
     * </p>
     * 
     * @param offset int
     * @param size   int
     * @return List<UserDTO>
     */
    @Override
    public List<UserDTO> doGetUserList(int offset, int size) {
        return this.getUsers(userDao.dbGetUsers(offset, size));
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
        return new UserDTO(userDao.dbGetUserById(id));
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
        return new UserDTO(userDao.dbGetUserByEmail(email));
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
     * @param user UserForm
     * @return int
     */
    @Override
    public int doUpdateUser(UserForm user) {
        userDao.dbUpdateUser(new User(user));
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

    /**
     * <h2>getUsers</h2>
     * <p>
     * Get User List from User Entity List
     * </p>
     * 
     */
    private List<UserDTO> getUsers(List<User> list) {
        return list.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
    }

    /**
     * <h2>doCheckUserCredential</h2>
     * <p>
     * Check User Credential
     * </p>
     * 
     * @param email String
     * @param password String
     */
    @Override
    public int doCheckUserCredential(String email, String password) {
        User user = userDao.dbGetUserByCredential(email, password);
        if (user == null) {
            return Constant.INVALID_CREDENTIAL;
        }
        return Constant.SUCCESS;
    }

    /**
     * <h2>loadUserByUsername</h2>
     * <p>
     * Load User by Name
     * </p>
     * 
     * @param username String
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = this.userDao.dbGetUserByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        return new UserDTO(userEntity);
    }
}
