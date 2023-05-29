package com.bulletinBoard.system.bl.service.asset;

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
}
