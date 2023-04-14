package com.bulletinBoard.system.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>UserForm Class</h2>
 * <p>
 * User Form
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserForm {

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
    @NotBlank(message = "Name is Required")
    private String name;

    /**
     * <h2>email</h2>
     * <p>
     * User's Email
     * </p>
     */
    @NotBlank(message = "Email is Required")
    private String email;

    /**
     * <h2>address</h2>
     * <p>
     * User's Address
     * </p>
     */
    @NotBlank(message = "Address is Required")
    private String address;

    /**
     * <h2>password</h2>
     * <p>
     * User's Password
     * </p>
     */
    @NotBlank(message = "Password is Required")
    private String password;
}
