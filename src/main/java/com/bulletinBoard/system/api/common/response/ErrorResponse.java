package com.bulletinBoard.system.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Object detail;

    /**
     * <h2> Constructor for ErrorResponse </h2>
     * <p>
     * Constructor for ErrorResponse
     * </p>
     */
    public ErrorResponse(String message, Object detail) {
        super(false, message);
        this.detail = detail;
    }
}
