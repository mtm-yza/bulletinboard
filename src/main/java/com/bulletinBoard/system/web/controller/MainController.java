package com.bulletinBoard.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    
    @GetMapping("/login")
    protected ModelAndView login() {
        return new ModelAndView("userLogin");
    }

    @RequestMapping("error")
    protected ModelAndView error() {
        return new ModelAndView("error");
    }
}
