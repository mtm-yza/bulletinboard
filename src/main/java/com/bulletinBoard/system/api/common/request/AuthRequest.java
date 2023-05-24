package com.bulletinBoard.system.api.common.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>AuthRequest Class</h2>
 * <p>
 * Process for Displaying AuthRequest
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Data
@NoArgsConstructor
public class AuthRequest {

    /**
     * <h2>userName</h2>
     * <p>
     * User Name
     * </p>
     */
    @NotNull
    @NotBlank(message = "Username is Required")
    private String userName;

    /**
     * <h2>password</h2>
     * <p>
     * Password
     * </p>
     */
    @NotNull
    @NotBlank(message = "Password is Required")
    private String password;
}
