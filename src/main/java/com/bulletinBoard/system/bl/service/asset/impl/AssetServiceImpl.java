package com.bulletinBoard.system.bl.service.asset.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.bl.service.asset.AssetService;
import com.bulletinBoard.system.bl.service.user.UserService;
import com.bulletinBoard.system.common.Constant;

/**
 * <h2>AssetServiceImpl Class</h2>
 * <p>
 * Implementation Class of AssetService
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Service
public class AssetServiceImpl implements AssetService {

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>doGetProfileUrl</h2>
     * <p>
     * Get Profile URL
     * </p>
     * 
     * @param userEmail String
     *
     * @return String
     */
    public String doGetProfileUrl(String userEmail) {
        UserDTO user = this.userService.doGetUserByEmail(userEmail);
        return (user.getProfilePhotoName() != null) ? "/asset/profile/" + user.getProfilePhotoName() : null;
    }

    /**
     * <h2>doSaveProfilePic</h2>
     * <p>
     * Save Profile Picture
     * </p>
     * 
     * @param rootDir          String
     * @param userEmail        String
     * @param originalFileName String
     * @param profilePic       byte[]
     * 
     * @return boolean
     */
    @Override
    public boolean doSaveProfilePic(String rootDir, String userEmail, String originalFileName, byte[] profilePic) {
        // Create File in Access Directory
        String newFileName = this.getFileNameByMilliSeconds(originalFileName);
        this.createDirectoryIfNotExist(this.getProfileFile(rootDir, ""));
        this.createFile(this.getProfileFile(rootDir, newFileName), profilePic);
        // Delete Existing File
        UserDTO user = this.userService.doGetUserByEmail(userEmail);
        if (user.getProfilePhotoName() != null) {
            this.deleteFile(getProfileFile(rootDir, user.getProfilePhotoName()));
        }
        // Update To Database
        user.setProfilePhotoName(newFileName);
        this.userService.doUpdateProfilePhoto(user.getId(), newFileName);
        return true;
    }

    /**
     * <h2>doDeleteProfilePic</h2>
     * <p>
     * Delete Profile Picture
     * </p>
     * 
     * @param rootDir String
     * @param email   String
     * @return boolean
     */
    @Override
    public boolean doDeleteProfilePic(String rootDir, String email) {
        UserDTO user = this.userService.doGetUserByEmail(email);
        File file = this.getProfileFile(rootDir, user.getProfilePhotoName());
        this.deleteFile(file);
        this.userService.doUpdateProfilePhoto(user.getId(), null);
        return true;
    }

    /**
     * <h2>getProfileFile</h2>
     * <p>
     * Get the File of Profile
     * </p>
     *
     * @param rootDir  String
     * @param fileName String
     *
     * @return File
     */
    private File getProfileFile(String rootDir, String fileName) {
        String profileFileName = new StringBuilder(this.getAssetDir(rootDir))
                .append(String.format(Constant.DIRECTORY_FORMAT, File.separator, "profile"))
                .append(String.format(Constant.DIRECTORY_FORMAT, File.separator, fileName)).toString();
        return new File(profileFileName);
    }

    /**
     * <h2>getAssetDir</h2>
     * <p>
     * Get Asset Directory
     * </p>
     *
     * @param rootDir String
     *
     * @return String
     */
    private String getAssetDir(String rootDir) {
        return new StringBuilder(rootDir).append(String.format(Constant.DIRECTORY_FORMAT, File.separator, "asset"))
                .toString();
    }

    /**
     * <h2>getFileNameByMiliSeconds</h2>
     * <p>
     * Get File Name by Milliseconds
     * </p>
     *
     * @param originalFileName String
     *
     * @return String
     */
    private String getFileNameByMilliSeconds(String originalFileName) {
        String[] fileNames = originalFileName.split("\\.");
        String fileExt = fileNames[fileNames.length - 1];
        return (new Date()).getTime() + "." + fileExt;
    }

    /**
     * <h2>createDirectoryIfNotExist</h2>
     * <p>
     * Create Directory
     * </p>
     *
     * @param directory String
     *
     * @return boolean
     */
    private boolean createDirectoryIfNotExist(File directory) {
        if (!directory.exists()) {
            return directory.mkdirs();
        }
        return true;
    }

    /**
     * <h2>createFile</h2>
     * <p>
     * Create File
     * </p>
     *
     * @param file        File
     * @param byteToWrite byte[]
     *
     * @return boolean
     */
    private boolean createFile(File file, byte[] byteToWrite) {
        try (OutputStream stream = new FileOutputStream(file)) {
            stream.write(byteToWrite);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * <h2>deleteFile</h2>
     * <p>
     * Delete File
     * </p>
     *
     * @param file File
     *
     * @return boolean
     */
    private boolean deleteFile(File file) {
        try {
            return Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            return false;
        }
    }
}
