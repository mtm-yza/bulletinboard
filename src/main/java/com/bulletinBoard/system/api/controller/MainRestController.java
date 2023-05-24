package com.bulletinBoard.system.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bulletinBoard.system.api.common.ControllerUtil;
import com.bulletinBoard.system.api.common.JwtTokenUtil;
import com.bulletinBoard.system.api.common.request.AuthRequest;
import com.bulletinBoard.system.api.common.response.AuthResponse;
import com.bulletinBoard.system.api.common.response.ErrorResponse;
import com.bulletinBoard.system.api.common.response.MainResponse;
import com.bulletinBoard.system.bl.dto.UserDTO;

/**
 * <h2>MainRestController Class</h2>
 * <p>
 * Process for Displaying MainRestController
 * </p>
 * 
 * @author YeZawAung
 *
 */
@RestController
@RequestMapping("/api/")
public class MainRestController {

    /**
     * <h2>authManager</h2>
     * <p>
     * authManager
     * </p>
     */
    @Autowired
    @Qualifier("authManager")
    AuthenticationManager authManager;

    /**
     * <h2>jwtTokenUtil</h2>
     * <p>
     * jwtTokenUtil
     * </p>
     */
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * <h2>login</h2>
     * <p>
     * User Login
     * </p>
     *
     * @param authRequest   AuthRequest
     * @param bindingResult BindingResult
     *
     * @return ResponseEntity<MainResponse>
     */
    @PostMapping("/login")
    public ResponseEntity<MainResponse> login(@RequestBody @Valid AuthRequest authRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Map<String, String>> errorList = ControllerUtil.getErrors(bindingResult);
            return new ResponseEntity<>(new ErrorResponse("Login Error", errorList), HttpStatus.BAD_REQUEST);
        }
        try {
            Authentication auth = this.authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            UserDetails user = (UserDTO) auth.getPrincipal();
            jwtTokenUtil.generateAccessToken(user);
            String token = jwtTokenUtil.generateAccessToken(user);
            MainResponse response = new AuthResponse(token, jwtTokenUtil.getExpiration(token));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ErrorResponse("Login Fail"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * <h2>getAccessDeniedError</h2>
     * <p>
     * Get Access Denied Error Response
     * </p>
     *
     * @return ResponseEntity<MainResponse>
     */
    @GetMapping("accessDenied")
    public ResponseEntity<MainResponse> getAccessDeniedError() {
        return new ResponseEntity<>(new ErrorResponse("Access Denied"), HttpStatus.FORBIDDEN);
    }
}
