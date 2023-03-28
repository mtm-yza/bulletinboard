package com.bulletinBoard.system.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.bl.service.PostService;
import com.bulletinBoard.system.web.form.PostForm;
import com.google.gson.Gson;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for Displaying PostController
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Controller
@RequestMapping("/post")
public class PostController {

    /**
     * <h2>HOME_VIEW</h2>
     * <p>
     * View Name Of Home From PostController
     * </p>
     */
    private static final String HOME_VIEW = "postListView";

    /**
     * <h2>HOME_REDIRECT</h2>
     * <p>
     * View Name of Home From PostController
     * </p>
     */
    private static final String HOME_REDIRECT = "redirect:/post/list";

    /**
     * <h2>ADD_VIEW</h2>
     * <p>
     * Redirect Link of Home from PostConteroller
     * </p>
     */
    private static final String ADD_VIEW = "addPostView";

    /**
     * <h2>EDIT_VIEW</h2>
     * <p>
     * View Name of Add Post Form from PostController
     * </p>
     */
    private static final String EDIT_VIEW = "editPostView";

    /**
     * <h2>service</h2>
     * <p>
     * Post Service
     * </p>
     */
    @Autowired
    PostService service;

    /**
     * <h2>getHomeView</h2>
     * <p>
     * Get Home view
     * </p>
     *
     * @param page int
     * @param size int
     * @return ModelAndView
     */
    @GetMapping({ "/", "" })
    protected ModelAndView getHomeView(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        return new ModelAndView(HOME_REDIRECT);
    }

    /**
     * <h2>getPostListView</h2>
     * <p>
     * Get Post List View
     * </p>
     *
     * @param page int
     * @param size int
     * @return mv ModelAndView
     */
    @GetMapping("list")
    protected ModelAndView getPostListView(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        ModelAndView mv = new ModelAndView(HOME_VIEW);
        // Get List of Post by offset
        int offset = (page - 1) * size;
        List<PostDTO> postDto = service.doGetPostList(offset, size);
        // Calculate Total Page for Pagination
        int count = service.doGetPostCount();
        int pageCount = count / size;
        int remainder = count % size;
        if (remainder > 0) {
            pageCount += 1;
        }
        mv.addObject("posts", (new Gson()).toJson(postDto));
        mv.addObject("pageIndex", page);
        mv.addObject("pageCount", pageCount);
        mv.addObject("pageSize", size);
        return mv;
    }

    /**
     * <h2>getAddPostForm</h2>
     * <p>
     * Get Add Post Form
     * </p>
     *
     * @return ModelAndView
     */
    @GetMapping("add")
    protected ModelAndView getAddPostForm() {
        return new ModelAndView(ADD_VIEW);
    }

    /**
     * <h2>addPost</h2>
     * <p>
     * Add Post
     * </p>
     *
     * @param title       String
     * @param description String
     * @param status      int
     * @return mv ModelAndView
     */
    @PostMapping("add")
    protected ModelAndView addPost(@RequestParam("title") String title, @RequestParam("description") String description,
            @RequestParam("status") int status) {
        PostForm post = new PostForm(title, description, status);
        ModelAndView mv = new ModelAndView();
        if (!validate(post, mv, ADD_VIEW)) {
            return mv;
        }
        boolean isSuccess = service.doAddPost(post);
        if (!isSuccess) {
            mv.addObject("msg", "Unable to Save the Post");
            mv.addObject("errors", Arrays.asList("Title Must Be Unique."));
            mv.setViewName(ADD_VIEW);
            return mv;
        }
        mv.setViewName(HOME_REDIRECT);
        return mv;
    }

    /**
     * <h2>getEditPostForm</h2>
     * <p>
     * Get Edit Post Form
     * </p>
     *
     * @return mv ModelAndView
     */
    @GetMapping("update")
    protected ModelAndView getEditPostForm() {
        return new ModelAndView(EDIT_VIEW);
    }

    /**
     * <h2>updatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param id          int
     * @param title       String
     * @param description String
     * @param status      String
     * @param flag        int
     * @return mv ModelAndView
     */
    @PostMapping("update")
    protected ModelAndView updatePost(@RequestParam("id") int id, @RequestParam("title") String title,
            @RequestParam("description") String description, @RequestParam("status") int status,
            @RequestParam("flag") int flag, HttpServletResponse resp) {
        ModelAndView mv = new ModelAndView(HOME_REDIRECT);
        PostForm post = new PostForm(id, title, description, status);
        if (!validate(post, mv, HOME_REDIRECT)) {
            return mv;
        }
        service.doUpdatePost(post, flag);
        return mv;
    }

    /**
     * <h2>deletePost</h2>
     * <p>
     * Delete Post
     * </p>
     *
     * @param id int
     * @return mv ModelAndView
     */
    @PostMapping("delete")
    protected ModelAndView deletePost(@RequestParam("id") int id) {
        ModelAndView mv = new ModelAndView();
        if (id <= 0) {
            mv.setViewName(EDIT_VIEW);
            mv.addObject("msg", "Invalid User ID");
            return mv;
        }
        service.doDeletePostById(id);
        mv.setViewName(HOME_REDIRECT);
        return mv;
    }

    /**
     * <h2>validate</h2>
     * <p>
     * Validate PostForm
     * </p>
     *
     * @param form     PostFrom
     * @param mv       ModelAndView
     * @param viewName String
     * @return boolean
     */
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
