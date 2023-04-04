package com.bulletinBoard.system.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * @param post              PostForm
     * @param bindingResult     BindingResult
     * @param redirectAttribute RedirectAttributes
     * @return mv ModelAndView
     */
    @PostMapping("add")
    protected ModelAndView addPost(@ModelAttribute @Valid PostForm post, BindingResult bindingResult,
            RedirectAttributes redirectAttribute) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView(ADD_VIEW);
            mv.addObject("post", post);
            mv.addObject("errors", this.getErrorMessages(bindingResult));
            return mv;
        }
        this.postService.doAddPost(post);
        ModelAndView mv = new ModelAndView(HOME_REDIRECT);
        this.addRedirectMessages(redirectAttribute, "success", "Adding Post Compeleted",
                "Your Post Was Successfully Added");
        return mv;
    }

    /**
     * <h2>getPostListView</h2>
     * <p>
     * Get Post List View
     * </p>
     *
     * @param page int
     * @param size int
     * @param post PostForm
     * @return mv ModelAndView
     */
    @GetMapping("list")
    protected ModelAndView getPostListView(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size, @ModelAttribute("post") PostForm post) {
        ModelAndView mv = new ModelAndView(HOME_VIEW);
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
        // Post Form to Edit
        if (post.getId() != 0) {
            mv.addObject("post", post);
        } else {
            mv.addObject("post", new PostForm());
        }
        return mv;
    }

    /**
     * <h2>updatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param post               PostForm
     * @param bindingResult      BindingResult
     * @param isStatusUpdate     Boolean
     * @param redirectAttributes RedirectAttributes
     * @return mv ModelAndView
     */
    @PostMapping("update")
    protected ModelAndView updatePost(@Valid @ModelAttribute PostForm post, BindingResult bindingResult,
            @RequestParam Boolean isStatusUpdate, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView(HOME_REDIRECT);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", this.getErrorMessages(bindingResult));
            redirectAttributes.addFlashAttribute("post", post);
            return mv;
        }
        if (isStatusUpdate) {
            this.postService.doEnableDisablePost(post);
        } else {
            this.postService.doUpdatePost(post);
        }
        this.addRedirectMessages(redirectAttributes, "success", "Updating Post Completed",
                "Your Post Was Successfully Updated");
        return mv;
    }

    /**
     * <h2>deletePost</h2>
     * <p>
     * Delete Post
     * </p>
     *
     * @param id                 int
     * @param redirectAttributes RedirectAttributes
     * @return mv ModelAndView
     */
    @PostMapping("delete")
    protected ModelAndView deletePost(@RequestParam int id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView(HOME_REDIRECT);
        if (id <= 0) {
            this.addRedirectMessages(redirectAttributes, "error", "Invalid ID", "Your Post ID Is Invalid");
            return mv;
        }
        this.postService.doDeletePostById(id);
        this.addRedirectMessages(redirectAttributes, "success", "Deleting Post Completed",
                "Your Post Was Successfuly Deleted");
        return mv;
    }

    /**
     * <h2>getErrorMessages</h2>
     * <p>
     * Get A List of Error Messages from BindingResult
     * </p>
     *
     * @param bindingResult BindingResult
     * @return Map<String, String>
     */
    private Map<String, String> getErrorMessages(BindingResult bindingResult) {
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
    private void addRedirectMessages(RedirectAttributes redirectAttributes, String type, String header,
            String message) {
        redirectAttributes.addFlashAttribute("msgType", type);
        redirectAttributes.addFlashAttribute("msgHeader", header);
        redirectAttributes.addFlashAttribute("msg", message);
    }
}
