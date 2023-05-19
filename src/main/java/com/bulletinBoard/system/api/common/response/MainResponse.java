package com.bulletinBoard.system.api.common.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainResponse {

    /**
     * <h2>isSuccess</h2>
     * <p>
     * isSuccess
     * </p>
     */
    private Boolean isSuccess;

    /**
     * <h2>message</h2>
     * <p>
     * Response Message
     * </p>
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * <h2>timestamp</h2>
     * <p>
     * Response Timestamp
     * </p>
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp = new Date();

    /**
     * <h2>data</h2>
     * <p>
     * data
     * </p>
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /**
     * <h2>Constructor for MainResponse</h2>
     * <p>
     * Constructor for MainResponse
     * </p>
     * 
     * @param isSuccess Boolean
     * @param message   String
     */
    public MainResponse(Boolean isSuccess, String message) {
        this.message = message;
        this.isSuccess = true;
    }

    /**
     * <h2>Constructor for MainResponse</h2>
     * <p>
     * Constructor for MainResponse
     * </p>
     * 
     * @param isSuccess String
     * @param data      Object
     */
    public MainResponse(String message, Object data) {
        this.message = message;
        this.isSuccess = true;
        this.data = data;
    }

    /**
     * <h2>Constructor for MainResponse</h2>
     * <p>
     * Constructor for MainResponse
     * </p>
     * 
     * @param message String
     */
    public MainResponse(String message) {
        this.isSuccess = true;
        this.message = message;
    }

    /**
     * <h2>Constructor for MainResponse</h2>
     * <p>
     * Constructor for MainResponse
     * </p>
     * 
     * @param data Object
     */
    public MainResponse(Object data) {
        this.isSuccess = true;
        this.data = data;
    }
}
