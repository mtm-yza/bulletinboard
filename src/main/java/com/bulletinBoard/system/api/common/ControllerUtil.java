package com.bulletinBoard.system.api.common;

/**
 * <h2>ControllerUtil Class</h2>
 * <p>
 * Utility Class for Controllers
 * </p>
 * 
 * @author YeZawAung
 *
 */
public class ControllerUtil {

    /**
     * <h2>PAGE_SIZE</h2>
     * <p>
     * Default Page Size of REST API
     * </p>
     */
    public final static int PAGE_SIZE = 10;

    /**
     * <h2>getOffset</h2>
     * <p>
     * Calculate and Get Offset
     * </p>
     *
     * @param page       int
     * @param totalCount int
     *
     * @return int
     */
    public static int getOffset(int page, int totalCount) {
        int pageIndex = (page == 0) ? 1 : page;
        return (pageIndex - 1) * PAGE_SIZE;
    }
}
