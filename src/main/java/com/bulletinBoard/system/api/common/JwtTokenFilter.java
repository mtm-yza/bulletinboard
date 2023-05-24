package com.bulletinBoard.system.api.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <h2>JwtTokenFilter Class</h2>
 * <p>
 * Process for Displaying JwtTokenFilter
 * </p>
 * 
 * @author YeZawAung
 *
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    /**
     * <h2>jwtUtil</h2>
     * <p>
     * jwtUtil
     * </p>
     */
    @Autowired
    private JwtTokenUtil jwtUtil;

    /**
     * <h2>doFilterInternal</h2>
     * <p>
     * Do Filter Internal
     * </p>
     * 
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!this.hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = this.getAccessToken(request);
        if (!this.jwtUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        this.setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }

    /**
     * <h2>hasAuthorizationBearer</h2>
     * <p>
     * Has AuthorizationBearer
     * </p>
     *
     * @param request HttpServletRequest
     *
     * @return boolean
     */
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
        return true;
    }

    /**
     * <h2>getAccessToken</h2>
     * <p>
     * Get Access Token
     * </p>
     *
     * @param request HttpServletRequest
     *
     * @return String token
     */
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    /**
     * <h2>setAuthenticationContext</h2>
     * <p>
     * Set Authentication Context
     * </p>
     *
     * @param token   String
     * @param request HttpServletRequest
     */
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = this.jwtUtil.getUserDetail(token);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(), userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
