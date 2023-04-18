package com.bulletinBoard.system.bl.dto;

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
public class UserDTO {

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
     * <h2>role</h2>
     * <p>
     * User's Role
     * </p>
     */
    private int role;

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
        this.role = user.getRole();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.password = user.getPassword();
    }
}
