package com.bulletinBoard.system.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ControllerUtil {

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
}
