package com.bulletinBoard.system.api.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>ErrorResponse Class</h2>
 * <p>
 * Process for Displaying ErrorResponse
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends MainResponse {

    /**
     * <h2>Error Detail Message</h2>
     * <p>
     * detail
     * </p>
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object detail;

    /**
     * <h2>Constructor for ErrorResponse</h2>
     * <p>
     * Constructor for ErrorResponse
     * </p>
     * 
     * @param message String
     */
    public ErrorResponse(String message) {
        super(false, message);
    }

    /**
     * <h2>Constructor for ErrorResponse</h2>
     * <p>
     * Constructor for ErrorResponse
     * </p>
     * 
     * @param message String
     * @param detail  Object
     */
    public ErrorResponse(String message, Object detail) {
        super(false, message);
        this.detail = detail;
    }
}
