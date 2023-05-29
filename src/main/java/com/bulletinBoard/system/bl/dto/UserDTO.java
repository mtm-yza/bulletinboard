package com.bulletinBoard.system.bl.dto;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bulletinBoard.system.persistance.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>UserDTO Class</h2>
 * <p>
 * Data Transfer Object for User
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO implements UserDetails {

    /**
     * <h2>serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <h2>id</h2>
     * <p>
     * User's ID
     * </p>
     */
    private int id;

    /**
     * <h2>name</h2>
     * <p>
     * User's Name
     * </p>
     */
    private String name;

    /**
     * <h2>email</h2>
     * <p>
     * User's Email
     * </p>
     */
    private String email;

    /**
     * <h2>address</h2>
     * <p>
     * User's Address
     * </p>
     */
    private String address;

    /**
     * <h2>password</h2>
     * <p>
     * User's Password
     * </p>
     */
    private String password;

    /**
     * <h2> profilePhotoName</h2>
     * <p>
     * profilePhotoName
     * </p>
     */
    private String profilePhotoName;

    /**
     * <h2>authorities</h2>
     * <p>
     * User's Authority
     * </p>
     */
    private AuthorityDTO authority;

    /**
     * <h2>getAuthorities</h2>
     * <p>
     * Get Authorities
     * </p>
     * 
     * @return list Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authority.getName()));
    }

    /**
     * <h2>getUsername</h2>
     * <p>
     * Get User Name
     * </p>
     * 
     * @return String
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * <h2>isAccountNonExpired</h2>
     * <p>
     * Check if account is non expired
     * </p>
     * 
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * <h2>isAccountNonLocked</h2>
     * <p>
     * Check if account is non locked
     * </p>
     * 
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * <h2>isCredentialsNonExpired</h2>
     * <p>
     * Check if credentials non expired
     * </p>
     * 
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * <h2>isEnabled</h2>
     * <p>
     * Check if the account is enabled
     * </p>
     * 
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * <h2>Constructor for UserDTO</h2>
     * <p>
     * Constructor for UserDTO
     * </p>
     * 
     * @param user User
     */
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.password = user.getPassword();
        this.profilePhotoName = user.getProfilePhotoName();
        this.authority = new AuthorityDTO(user.getAuthority());
    }
}
