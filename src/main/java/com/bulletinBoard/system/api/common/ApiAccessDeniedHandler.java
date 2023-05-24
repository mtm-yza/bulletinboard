package com.bulletinBoard.system.api.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.bulletinBoard.system.api.common.response.ErrorResponse;
import com.google.gson.Gson;

/**
 * <h2>ApiAccessDeniedHandler Class</h2>
 * <p>
 * Process for Displaying ApiAccessDeniedHandler
 * </p>
 * 
 * @author YeZawAung
 *
 */
public class ApiAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * <h2>handle</h2>
     * <p>
     * Handle
     * </p>
     * 
     * @param request               HttpServletRequest
     * @param response              HttpServletResponse
     * @param accessDeniedException AccessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(new Gson().toJson(new ErrorResponse("Access Denied")));
    }
}
