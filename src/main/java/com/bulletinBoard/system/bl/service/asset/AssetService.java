package com.bulletinBoard.system.bl.service.asset;

import java.util.List;

/**
 * <h2>AssetService Class</h2>
 * <p>
 * Process for Displaying AssetService
 * </p>
 * 
 * @author YeZawAung
 *
 */
/**
 * <h2>AssetService Class</h2>
 * <p>
 * Process for Displaying AssetService
 * </p>
 * 
 * @author YeZawAung
 *
 */
public interface AssetService {

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
    public String doGetProfileUrl(String userEmail);

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
    public boolean doSaveProfilePic(String rootDir, String userEmail, String originalFileName, byte[] profilePic);

    /**
     * <h2>doDeleteProfilePic</h2>
     * <p>
     * Delete Profile Picture
     * </p>
     *
     * @param rootDir   String
     * @param userEmail String
     *
     * @return boolean
     */
    public boolean doDeleteProfilePic(String rootDir, String userEmail);

    /**
     * <h2>doGetImageUrls</h2>
     * <p>
     * Get Image URLs
     * </p>
     *
     * @param postId int
     *
     * @return List<String>
     */
    public List<String> doGetImageUrls(int postId);

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
    public boolean doUploadPostImages(String rootDir, int postId, List<String> originalFileNames, List<byte[]> images);

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
    public boolean doDeletePostImages(String rootDir, int postId, String fileName);

}
