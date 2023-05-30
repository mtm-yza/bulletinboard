package com.bulletinBoard.system.bl.service.asset.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.bl.service.asset.AssetService;
import com.bulletinBoard.system.bl.service.post.PostService;
import com.bulletinBoard.system.bl.service.user.UserService;

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
     * <h2>postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;

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
     * <h2>doGetImageUrls</h2>
     * <p>
     * Get Image URLs
     * </p>
     * 
     * @param postId int
     * @return List<String>
     */
    @Override
    public List<String> doGetImageUrls(int postId) {
        PostDTO post = this.postService.doGetPostById(postId);
        return post.getImageNames().stream().map(item -> "/asset/post/img/" + item).collect(Collectors.toList());
    }

    /**
     * <h2>doUploadPostImages</h2>
     * <p>
     * Upload Post Images
     * </p>
     * 
     * @param rootDir           String
     * @param postId            int
     * @param originalFileNames List<String>
     * @param images            List<byte[]>
     *
     * @return boolean
     */
    @Override
    public boolean doUploadPostImages(String rootDir, int postId, List<String> originalFileNames, List<byte[]> images) {
        // Create Directory
        this.createDirectoryIfNotExist(this.getPostImgFile(rootDir, ""));
        // Save in Asset Directory
        List<String> newFileNames = new ArrayList<>();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        IntStream.range(0, images.size()).forEach(index -> {
            String fileName = new StringBuilder(Integer.toString(postId)).append("-")
                    .append(this.getFileNameByMilliSeconds(originalFileNames.get(index))).toString();
            this.createFile(this.getPostImgFile(rootDir, fileName), images.get(index));
            newFileNames.add(fileName);
            // Wait to Avoid Duplicate Name
            try {
                executorService.awaitTermination(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // Update Database
        PostDTO post = this.postService.doGetPostById(postId);
        if (!post.getImageNames().isEmpty()) {
            post.getImageNames().forEach(item -> {
                this.deleteFile(this.getPostImgFile(rootDir, item));
                System.out.println("Deleted File " + item);
            });
        }
        this.postService.doUpdateImages(postId, newFileNames);
        return true;
    }

    /**
     * <h2>doDeletePostImages</h2>
     * <p>
     * Delete Post Images
     * </p>
     * 
     * @param rootDir  String
     * @param postId   int
     * @param fileName String
     *
     * @return boolean
     */
    @Override
    public boolean doDeletePostImages(String rootDir, int postId, String fileName) {
        PostDTO post = this.postService.doGetPostById(postId);
        this.deleteFile(this.getPostImgFile(rootDir, fileName));
        post.getImageNames().remove(fileName);
        this.postService.doUpdateImages(postId, post.getImageNames());
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
        return Paths.get(this.getAssetDir(rootDir), "profile", fileName).toFile();
    }

    /**
     * <h2>getPostImgFile</h2>
     * <p>
     * Get Post Image File
     * </p>
     *
     * @param rootDir  String
     * @param fileName String
     *
     * @return File
     */
    private File getPostImgFile(String rootDir, String fileName) {
        return Paths.get(this.getAssetDir(rootDir), "post", "img", fileName).toFile();
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
        return Paths.get(rootDir, "asset").toString();
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
