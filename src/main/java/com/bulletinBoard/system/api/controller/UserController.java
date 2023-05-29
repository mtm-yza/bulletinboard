package com.bulletinBoard.system.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bulletinBoard.system.api.common.ControllerUtil;
import com.bulletinBoard.system.api.common.response.ErrorResponse;
import com.bulletinBoard.system.api.common.response.MainResponse;
import com.bulletinBoard.system.api.response.UserResponse;
import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.bl.service.asset.AssetService;
import com.bulletinBoard.system.bl.service.user.UserService;
import com.bulletinBoard.system.common.Constant;
import com.bulletinBoard.system.web.form.UserForm;

/**
 * <h2>UserController Class</h2>
 * <p>
 * Controller Class for Displaying UserController
 * </p>
 * 
 * @author YeZawAung
 *
 */
@RestController("userApiController")
@RequestMapping("/api/user")
public class UserController {

    /**
     * <h2>PROFILE_RESOURCE_URL</h2>
     * <p>
     * PROFILE_RESOURCE_URL
     * </p>
     */
    private static final String PROFILE_RESOURCE_URL = "/api/resources/";

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>assetService</h2>
     * <p>
     * assetService
     * </p>
     */
    @Autowired
    private AssetService assetService;

    /**
     * <h2>addUser</h2>
     * <p>
     * Add User
     * </p>
     *
     * @param user          UserForm
     * @param bindingResult BindingResult
     *
     * @return ResponseEntity<MainResponse>
     */
    @PostMapping({ "", "/" })
    public ResponseEntity<MainResponse> addUser(@Valid @RequestBody UserForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Map<String, String>> errorList = ControllerUtil.getErrors(bindingResult);
            return new ResponseEntity<>(new ErrorResponse("Failed to Add User", errorList), HttpStatus.BAD_REQUEST);
        }
        int result = this.userService.doAddUser(user);
        if (result != Constant.SUCCESS) {
            return new ResponseEntity<>(new ErrorResponse(Constant.getMessage(result)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MainResponse("Saving Successfully"), HttpStatus.CREATED);
    }

    /**
     * <h2>getUsers</h2>
     * <p>
     * Get Users
     * </p>
     *
     * @param page int
     *
     * @return ResponseEntity<MainResponse>
     */
    @GetMapping({ "", "/" })
    public ResponseEntity<MainResponse> getUsers(@RequestParam(defaultValue = "0") int page) {
        int count = userService.doGetUserCount();
        int offset = ControllerUtil.getOffset(page, count);
        List<UserDTO> users = this.userService.doGetUserList(offset, ControllerUtil.PAGE_SIZE);
        return new ResponseEntity<>(new MainResponse(this.getUserResponses(users)), HttpStatus.OK);
    }

    /**
     * <h2>getUser</h2>
     * <p>
     * Get User
     * </p>
     *
     * @param id int
     *
     * @return ResponseEntity<MainResponse>
     */
    @GetMapping("/{id}")
    public ResponseEntity<MainResponse> getUser(@PathVariable int id) {
        if (id == 0) {
            return new ResponseEntity<>(new ErrorResponse("Invalid ID"), HttpStatus.BAD_REQUEST);
        }
        UserDTO user = userService.doGetUserById(id);
        if (user == null) {
            return new ResponseEntity<>(new ErrorResponse("User Not Found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MainResponse(new UserResponse(user)), HttpStatus.OK);
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param user          UserForm
     * @param bindingResult BindingResult
     *
     * @return ResponseEntity<MainResponse>
     */
    @PutMapping({ "", "/" })
    public ResponseEntity<MainResponse> updateUser(@Valid @RequestBody UserForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Map<String, String>> errorList = ControllerUtil.getErrors(bindingResult);
            return new ResponseEntity<>(new ErrorResponse("Failed To Update User", errorList), HttpStatus.BAD_REQUEST);
        }
        if (this.userService.doGetUserById(user.getId()) == null) {
            return new ResponseEntity<>(new ErrorResponse("User Not Found"), HttpStatus.OK);
        }
        this.userService.doUpdateUser(user);
        UserResponse userResponse = new UserResponse(this.userService.doGetUserById(user.getId()));
        return new ResponseEntity<>(new MainResponse("Update Successful", userResponse), HttpStatus.OK);
    }

    /**
     * <h2>deleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id int
     *
     * @return ResponseEntity<MainResponse>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MainResponse> deleteUser(@PathVariable int id) {
        if (id == 0) {
            return new ResponseEntity<>(new ErrorResponse("Invalid ID"), HttpStatus.BAD_REQUEST);
        }
        if (this.userService.doGetUserById(id) == null) {
            return new ResponseEntity<>(new ErrorResponse("User Not Found"), HttpStatus.OK);
        }
        this.userService.doDeleteUser(id);
        return new ResponseEntity<>(new MainResponse("Delete Successfully"), HttpStatus.OK);
    }

    /**
     * <h2>uploadPicture</h2>
     * <p>
     * Upload Picture
     * </p>
     *
     * @param pic     MultipartFile
     * @param request HttpServletRequest
     *
     * @return ResponseEntity<MainResponse>
     */
    @PostMapping("/profile/photo")
    public ResponseEntity<MainResponse> uploadPicture(@RequestPart MultipartFile pic, HttpServletRequest request,
            Authentication auth) {
        // Check if the Picture is Empty
        if (pic.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse("Image is Required"), HttpStatus.BAD_REQUEST);
        }
        // Validate File Content Type
        if (!pic.getContentType().matches("image/(png|jpeg|gif|svg|heif*|heic*|)")) {
            return new ResponseEntity<>(new ErrorResponse("Unsupported File Extension"),
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        // Save Profile Picture
        byte[] picByte = null;
        try {
            picByte = pic.getBytes();
        } catch (IOException e) {
            return new ResponseEntity<>(new ErrorResponse("Unable to Fetch File"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        this.assetService.doSaveProfilePic(this.getRootDir(request), auth.getName(), pic.getOriginalFilename(),
                picByte);
        return new ResponseEntity<>(new MainResponse("Saving Successfully", pic.getOriginalFilename()), HttpStatus.OK);
    }

    /**
     * <h2>getProfile</h2>
     * <p>
     * Get Profile
     * </p>
     *
     * @param request HttpServletRequest
     * @param auth    Authentication
     *
     * @return ResponseEntity<MainResponse>
     */
    @GetMapping(value = "/profile")
    public ResponseEntity<MainResponse> getProfile(HttpServletRequest request, HttpServletRequest response,
            Authentication auth) {
        UserDTO user = userService.doGetUserByEmail(auth.getName());
        if (user.getProfilePhotoName() != null) {
            String profileUrl = this.assetService.doGetProfileUrl(auth.getName());
            user.setProfilePhotoName(PROFILE_RESOURCE_URL + profileUrl);
        }
        return new ResponseEntity<>(new MainResponse(new UserResponse(user)), HttpStatus.OK);
    }

    /**
     * <h2>deleteProfile</h2>
     * <p>
     * Delete Profile Photo
     * </p>
     *
     * @param request HttpServletRequest
     * @param auth    Authentication
     *
     * @return ResponseEntity<MainResponse>
     */
    @DeleteMapping("/profile/photo")
    public ResponseEntity<MainResponse> deleteProfile(HttpServletRequest request, Authentication auth) {
        this.assetService.doDeleteProfilePic(this.getRootDir(request), auth.getName());
        return new ResponseEntity<>(new MainResponse("Delete Successfully"), HttpStatus.OK);
    }

    /**
     * <h2>getRootDir</h2>
     * <p>
     * Get Root Directory
     * </p>
     *
     * @param request HttpServletRequest
     *
     * @return String
     */
    private String getRootDir(HttpServletRequest request) {
        return new StringBuilder(request.getServletContext().getRealPath("resources")).toString();
    }

    /**
     * <h2>getUserResponses</h2>
     * <p>
     * Get User Responses from DTO
     * </p>
     *
     * @param list List<UserResponse>
     *
     * @return List<UserResponse>
     */
    private List<UserResponse> getUserResponses(List<UserDTO> list) {
        return list.stream().map(it -> new UserResponse(it)).collect(Collectors.toList());
    }
}
