package com.bulletinBoard.system.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bulletinBoard.system.bl.service.PostService;
import com.bulletinBoard.system.web.form.PostForm;

@Controller
@RequestMapping("/post")
public class PostController {
    
    private static final String HOME_VIEW = "postListView";
    private static final String HOME_REDIRECT = "redirect:list";
    private static final String ADD_VIEW = "addPostView";
    private static final String EDIT_VIEW = "editPostView"; 

    @Autowired
    PostService service;
    
    @GetMapping("list")
    protected ModelAndView getAllPosts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ModelAndView mv = new ModelAndView();
        mv.setViewName(HOME_VIEW);
        mv.addObject("posts", service.getByStatusActive());

        return mv;
    }

    @GetMapping("add")
    protected ModelAndView redirectAddPostForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ModelAndView mv = new ModelAndView();
        mv.setViewName(ADD_VIEW);

        return mv;
    }

    @PostMapping("add")
    protected ModelAndView addPost(@RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam("status") int status, HttpServletResponse resp) throws ServletException, IOException {

        PostForm post = new PostForm(title, description, status);
        ModelAndView mv = new ModelAndView();

        if (!validate(post, mv, ADD_VIEW)) {
            return mv;
        }

        service.add(post);
        mv.setViewName(HOME_REDIRECT);
        return mv;
    }
    
    @GetMapping("update")
    protected ModelAndView redirectEditPostForm(HttpServletRequest req, HttpServletResponse resp) {
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName(EDIT_VIEW);
        mv.addObject("posts", service.getAll());
        
        return mv;
    }

    @PostMapping("update")
    protected ModelAndView updatePost(@RequestParam("id") int id, @RequestParam("title") String title,
            @RequestParam("description") String description, @RequestParam("status") int status,
            HttpServletResponse resp) throws ServletException, IOException {

        PostForm post = new PostForm(id, title, description, status);
        ModelAndView mv = new ModelAndView();
        
        if (!validate(post, mv, EDIT_VIEW)) {
            mv.addObject("posts", service.getAll());
            return mv;
        }
        
        service.update(post);
        mv.setViewName(HOME_REDIRECT);

        return mv;
    }
    
    @PostMapping("delete")
    protected ModelAndView addPost(@RequestParam("id") int id, HttpServletResponse resp) throws ServletException, IOException {

        ModelAndView mv = new ModelAndView();
        
        if (id <= 0 ) {
            mv.setViewName(EDIT_VIEW);
            mv.addObject("msg", "Invalid User ID");
            return mv;
        }
        
        service.delete(id);
        mv.setViewName(HOME_REDIRECT);
        
        return mv;
    }

    private boolean validate(PostForm form, ModelAndView mv, String viewName) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<PostForm>> violations = validator.validate(form);
        List<String> errors = violations.stream().map(item -> item.getMessage()).collect(Collectors.toList());

        if (!errors.isEmpty()) {
            mv.setViewName(viewName);
            mv.addObject("msg", "Validation Error");
            mv.addObject("errors", errors);
            return false;
        }

        return true;
    }
}
