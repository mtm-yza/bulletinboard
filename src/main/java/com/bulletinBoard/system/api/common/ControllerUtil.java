package com.bulletinBoard.system.api.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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

    /**
     * <h2>getErrors</h2>
     * <p>
     * 
     * </p>
     *
     * @param bindingResult BindingResult
     * 
     * @return List<Map<String,String>>
     */
    public static List<Map<String, String>> getErrors(BindingResult bindingResult) {
        List<Map<String, String>> list = new LinkedList<>();
        bindingResult.getAllErrors().forEach(it -> {
            Map<String, String> errorMap = new HashMap<>();
            if (it instanceof FieldError) {
                FieldError error = (FieldError) it;
                errorMap.put("name", error.getField());
                errorMap.put("description", error.getDefaultMessage());
                list.add(errorMap);
            }
        });
        return list;
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
    public static String getRootDir(HttpServletRequest request) {
        return new StringBuilder(request.getServletContext().getRealPath("resources")).toString();
    }
}
