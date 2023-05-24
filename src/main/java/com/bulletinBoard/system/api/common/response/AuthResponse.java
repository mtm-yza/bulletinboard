package com.bulletinBoard.system.api.common.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>AuthResponse Class</h2>
 * <p>
 * Process for Displaying AuthResponse
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthResponse extends MainResponse {

    /**
     * <h2>token</h2>
     * <p>
     * User's Token
     * </p>
     */
    private String token;

    /**
     * <h2>expireAt</h2>
     * <p>
     * Token Expire Date
     * </p>
     */
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss:SSS")
    private Date expireAt;

    /**
     * <h2>Constructor for AuthResponse</h2>
     * <p>
     * Constructor for AuthResponse
     * </p>
     * 
     * @param token    String
     * @param expireAt Date
     */
    public AuthResponse(String token, Date expireAt) {
        super(true);
        this.token = token;
        this.expireAt = expireAt;
    }
}
