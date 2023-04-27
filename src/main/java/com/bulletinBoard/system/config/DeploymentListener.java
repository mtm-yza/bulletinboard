package com.bulletinBoard.system.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bulletinBoard.system.persistance.dao.authority.AuthorityDao;
import com.bulletinBoard.system.persistance.dao.user.UserDao;
import com.bulletinBoard.system.persistance.entity.Authority;
import com.bulletinBoard.system.persistance.entity.User;

/**
 * <h2>DeploymentListener Class</h2>
 * <p>
 * Class for Displaying DeploymentListener
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Component
public class DeploymentListener {

    /**
     * <h2>authorityDao</h2>
     * <p>
     * Data Access Object For Authority
     * </p>
     */
    @Autowired
    private AuthorityDao authorityDao;

    /**
     * <h2>userDao</h2>
     * <p>
     * Data Access Object For User
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>pwdEncoder</h2>
     * <p>
     * Password Encoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    /**
     * <h2>addInitialData</h2>
     * <p>
     * Add Intial Data
     * </p>
     *
     */
    @PostConstruct
    public void addInitialData() {
        // Check If the Default is Present or Not
        if (this.authorityDao.dbGetAuthorityCount() > 0 && this.userDao.dbGetUserCount() > 0) {
            return;
        }
        // Add Default Admin Account
        Authority adminAuthority = new Authority(null, "ROLE_ADMIN");
        authorityDao.dbInsertAuthority(adminAuthority);
        this.userDao.dbInsertUser(new User("Admin", 2, "admin@gmail.com", "Yangon", this.pwdEncoder.encode("admin"),
                Arrays.asList(adminAuthority)));
        // Add Default User Account
        Authority userAuthority = new Authority(null, "ROLE_USER");
        authorityDao.dbInsertAuthority(userAuthority);
        this.userDao.dbInsertUser(new User("User A", 1, "user-a@gmail.com", "Mandalay",
                this.pwdEncoder.encode("aaaaaa"), Arrays.asList(userAuthority)));
    }
}
