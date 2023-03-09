package com.bulletinBoard.system.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bulletinBoard.system.bl.service.PostService;
import com.bulletinBoard.system.bl.service.PostServiceImpl;
import com.bulletinBoard.system.persistance.entity.Posts;

@Controller
public class PostController extends HttpServlet {

    PostService service;

    public PostController() {
        service = new PostServiceImpl();
    }

    @RequestMapping("/post")
    protected ModelAndView getActivePosts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        SessionFactory factory = new Configuration()
                .addPackage("com.bulletinBoard.persistance.entity")
                .addAnnotatedClass(Posts.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = factory.openSession();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("WEB-INF/pages/displayPostController.jsp");
        mv.addObject("activePosts", session.createQuery("from Posts").list());
        
        return mv;
    }
}
