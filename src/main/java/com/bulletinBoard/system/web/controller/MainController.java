package com.bulletinBoard.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    /**
     * <h2>login</h2>
     * <p>
     * Login
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @GetMapping("/login")
    protected ModelAndView login() {
        return new ModelAndView("userLogin");
    }

    /**
     * <h2>error</h2>
     * <p>
     * Error Page
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("error")
    protected ModelAndView error() {
        return new ModelAndView("error");
    }
}
