package com.bulletinBoard.system.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bulletinBoard.system.api.common.response.ErrorResponse;
import com.bulletinBoard.system.api.common.response.MainResponse;

/**
 * <h2>AssetController Class</h2>
 * <p>
 * Process for Displaying AssetController
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Component
@RestController
@RequestMapping("/api/asset")
public class AssetController {

    /**
     * <h2>RESOURCE_MAP</h2>
     * <p>
     * RESOURCE_MAP
     * </p>
     */
    protected static final String RESOURCE_MAP = "/api/resources/asset/profile/";

    @PutMapping("/profile")
    public ResponseEntity<MainResponse> saveProfilePic(String fileName, MultipartFile pic, HttpServletRequest request) {
        // Get Absolute File Path
        String path = request.getServletContext().getRealPath("resources");
        String uploadFilePath = path + File.separator + "asset" + File.separator + "profile";
        // Check If the File Exist
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        // Write Image
        File imgFile = new File(uploadFilePath + File.separator + fileName);
        try (OutputStream stream = new FileOutputStream(imgFile)) {
            stream.write(pic.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>(new ErrorResponse("Unable to Save Image. Please Try Again"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new MainResponse("Saving Successfully"), HttpStatus.OK);
    }

    /**
     * <h2>getProfile</h2>
     * <p>
     * Get Profile Picture
     * </p>
     *
     * @param fileName String
     * @param request  HttpServletRequest
     *
     * @return String
     */
    @GetMapping("/profile")
    public String getProfilePic(@PathVariable String fileName, HttpServletRequest request, Authentication auth) {
        return (fileName != null) ? request.getContextPath() + RESOURCE_MAP + fileName : null;
    }

    /**
     * <h2>deleteProfilePic</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return ResponseEntity<MainResponse>
     */
    @DeleteMapping("/profile")
    public ResponseEntity<MainResponse> deleteProfilePic() {
        return null;
    }
}
