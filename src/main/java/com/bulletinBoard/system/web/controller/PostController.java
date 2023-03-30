package com.bulletinBoard.system.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.bl.service.post.PostService;
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
    private PostService postService;

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
    protected ModelAndView getHomeView() {
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
        // Post for Edit From
        mv.addObject("post", new PostForm());
        // Get List of Post by offset
        int offset = (page - 1) * size;
        List<PostDTO> postDto = this.postService.doGetPostList(offset, size);
        // Calculate Total Page for Pagination
        int count = this.postService.doGetPostCount();
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
        ModelAndView mv = new ModelAndView(ADD_VIEW);
        mv.addObject("post", new PostForm());
        return mv;
    }

    /**
     * <h2>addPost</h2>
     * <p>
     * Add Post
     * </p>
     *
     * @param post          PostForm
     * @param bindingResult BindingResult
     * @return mv ModelAndView
     */
    @PostMapping("add")
    protected ModelAndView addPost(@Valid @ModelAttribute PostForm post, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.addObject("msg", "Validation Error");
            mv.addObject("errors", this.getErrorMessages(bindingResult));
            mv.setViewName(ADD_VIEW);
            return mv;
        }
        this.postService.doAddPost(post);
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
     * @param post           PostForm
     * @param isStatusUpdate Boolean
     * @param bindingResult  BindingResult
     * @return mv ModelAndView
     */
    @PostMapping("update")
    protected ModelAndView updatePost(@ModelAttribute PostForm post, @RequestParam Boolean isStatusUpdate,
            BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView(HOME_REDIRECT);
        if (isStatusUpdate) {
            this.postService.doEnableDisablePost(post);
        } else {
            this.postService.doUpdatePost(post);
        }
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
    protected ModelAndView deletePost(@RequestParam int id) {
        ModelAndView mv = new ModelAndView();
        if (id <= 0) {
            mv.setViewName(EDIT_VIEW);
            mv.addObject("msg", "Invalid User ID");
            return mv;
        }
        this.postService.doDeletePostById(id);
        mv.setViewName(HOME_REDIRECT);
        return mv;
    }

    /**
     * <h2>getErrorMessages</h2>
     * <p>
     * Get A List of Error Messages from BindingResult
     * </p>
     *
     * @param bindingResult BindingResult
     * @return List<String>
     */
    private List<String> getErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.toList());
    }
}
