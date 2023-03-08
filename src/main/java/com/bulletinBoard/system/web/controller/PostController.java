package com.bulletinBoard.system.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bulletinBoard.system.bl.service.PostService;
import com.bulletinBoard.system.bl.service.PostServiceImpl;

@Controller
public class PostController extends HttpServlet {
    
    PostService service;
    
    public PostController() {
        service = new PostServiceImpl();
    }

    @RequestMapping("/post")
    protected ModelAndView getActivePosts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("WEB-INF/pages/displayPostController.jsp");
        mv.addObject("activePosts", service.getActivePosts());
        mv.addObject("message", "World");
        return mv;
    }
}
