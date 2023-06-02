package com.bulletinBoard.system.bl.service.user.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.bl.service.ServiceUtil;
import com.bulletinBoard.system.bl.service.user.UserService;
import com.bulletinBoard.system.common.Constant;
import com.bulletinBoard.system.persistance.entity.User;
import com.bulletinBoard.system.persistance.repository.AuthorityRepository;
import com.bulletinBoard.system.persistance.repository.UserRepository;
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
     * <h2>userRepository</h2>
     * <p>
     * User Repository
     * </p>
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * <h2>authRepository</h2>
     * <p>
     * Repository
     * </p>
     */
    @Autowired
    private AuthorityRepository authRepository;

    /**
     * <h2>pwdEncoder</h2>
     * <p>
     * Password Encoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

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
        Optional<User> userEntity = userRepository.dbGetUserByEmail(user.getEmail());
        if (userEntity.isPresent()) {
            return Constant.EMAIL_ALREADY_REGISTERED;
        }
        User newEntity = new User(user);
        int role = (user.getRole() == Constant.UserRole.ADMIN.getId()) ? 2 : 1;
        newEntity.setAuthority(authRepository.findById(role).orElse(null));
        newEntity.setPassword(this.pwdEncoder.encode(user.getPassword()));
        userRepository.save(newEntity);
        return Constant.SUCCESS;
    }

    /**
     * <h2>doGetUserList</h2>
     * <p>
     * Get User List
     * </p>
     * 
     * @param page int
     * @param size int
     * @return List<UserDTO>
     */
    @Override
    public List<UserDTO> doGetUserList(int page, int size) {
        Pageable paging = ServiceUtil.getPagable(page, size);
        Page<User> userPage = userRepository.findAll(paging);
        return this.getUsers(userPage.getContent());
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
        return userRepository.findById(id).map(UserDTO::new).orElse(null);
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
        return new UserDTO(userRepository.dbGetUserByEmail(email).orElse(null));
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
        return (int) userRepository.count();
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
        Optional<User> oldEntity = this.userRepository.findById(user.getId());
        if (oldEntity.isEmpty()) {
            return Constant.NOT_FOUND;
        }
        User userEntity = new User(user);
        int role = (user.getRole() == Constant.UserRole.ADMIN.getId()) ? 2 : 1;
        userEntity.setAuthority(authRepository.findById(role).orElse(null));
        userEntity.setPassword(oldEntity.get().getPassword());
        userRepository.save(userEntity);
        return Constant.SUCCESS;
    }

    /**
     * <h2>doUpdateProfilePhoto</h2>
     * <p>
     * 
     * </p>
     * 
     * @param userId           int
     * @param profilePhotoName String
     * @return int
     */
    @Override
    public int doUpdateProfilePhoto(int userId, String profilePhotoName) {
        if (!userRepository.existsById(userId)) {
            return Constant.NOT_FOUND;
        }
        User user = userRepository.findById(userId).get();
        user.setProfilePhotoName(profilePhotoName);
        userRepository.save(user);
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
        userRepository.deleteById(id);
    }

    /**
     * <h2>doCheckUserCredential</h2>
     * <p>
     * Check User Credential
     * </p>
     * 
     * @param email    String
     * @param password String
     */
    @Override
    public int doCheckUserCredential(String email, String password) {
        Optional<User> user = userRepository.dbGetUserByCredential(email, password);
        if (user.isEmpty()) {
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
        Optional<User> userEntity = this.userRepository.dbGetUserByEmail(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
        return new UserDTO(userEntity.get());
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
}
