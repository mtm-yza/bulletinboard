package com.bulletinBoard.system.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ControllerUtil {
    
    public final static int PAGE_SIZE = 10;

    /**
     * <h2>getErrorMessages</h2>
     * <p>
     * Get A List of Error Messages from BindingResult
     * </p>
     *
     * @param bindingResult BindingResult
     * @return errors Map<String, String>
     */
    public static Map<String, String> getErrorMessages(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    /**
     * <h2>addRedirectMessages</h2>
     * <p>
     * To add message data as redirect attribute
     * </p>
     *
     * @param redirectAttributes RedriectAttributes
     * @param type               String
     * @param header             String
     * @param message            String
     */
    public static void addRedirectMessages(RedirectAttributes redirectAttributes, String type, String header,
            String message) {
        redirectAttributes.addFlashAttribute("msgType", type);
        redirectAttributes.addFlashAttribute("msgHeader", header);
        redirectAttributes.addFlashAttribute("msg", message);
    }
    
    public static int setPaginationData(HttpSession session, int page, int totalCount) {
        int pageIndex = 0;
        // Calculate offset from Page Index
        if (page != 0) {
            pageIndex = page;
        } else {
            Object indexSession = session.getAttribute("pageIndex");
            pageIndex = (indexSession != null) ? (int) indexSession : 1;
        }
        session.setAttribute("totalCount", totalCount);
        session.setAttribute("pageIndex", pageIndex);
        session.setAttribute("pageSize", PAGE_SIZE);
        return (pageIndex - 1) * PAGE_SIZE;
    }
}
