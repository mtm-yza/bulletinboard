package com.bulletinBoard.system.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bulletinBoard.system.api.common.ControllerUtil;
import com.bulletinBoard.system.api.response.MessageResponse;
import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.bl.service.post.PostService;
import com.bulletinBoard.system.web.form.PostForm;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for PostController
 * </p>
 * 
 * @author YeZawAung
 *
 */
@RestController("postApiController")
@RequestMapping("/api/post")
public class PostController {

    /**
     * <h2>postService</h2>
     * <p>
     * Post Service
     * </p>
     */
    @Autowired
    PostService postService;

    /**
     * <h2>addPost</h2>
     * <p>
     * Add Post
     * </p>
     *
     * @param post          PostForm
     * @param bindingResult BindingResult
     *
     * @return MessageResponse
     */
    @PostMapping({ "", "/" })
    public MessageResponse addPost(@Valid @RequestBody PostForm post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new MessageResponse("Failed to Add Post");
        }
        post.setId(1);
        this.postService.doAddPost(post);
        return new MessageResponse("Saving Successfully");
    }

    /**
     * <h2>getPublicPosts</h2>
     * <p>
     * Get Public Posts
     * </p>
     *
     * @param page int
     *
     * @return List<PostDTO>
     */
    @GetMapping({ "", "/" })
    public List<PostDTO> getPublicPosts(@RequestParam(defaultValue = "0") int page) {
        String email = "admin@gmail.com";
        int count = this.postService.doGetPublicPostCount(email);
        int offset = ControllerUtil.getOffset(page, count);
        return this.postService.doGetPublicPosts(offset, ControllerUtil.PAGE_SIZE, email);
    }

    /**
     * <h2>getPostById</h2>
     * <p>
     * Get Post by ID
     * </p>
     *
     * @param id int
     *
     * @return PostDTO post
     */
    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable int id) {
        PostDTO post = this.postService.doGetPostById(id);
        return post;
    }

    /**
     * <h2>updatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param post          PostForm
     * @param bindingResult BindingResult
     *
     * @return Map<String,Object>
     */
    @PutMapping("/{id}")
    public Map<String, Object> updatePost(@RequestBody PostForm post, BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        this.postService.doUpdatePost(post);
        map.put("post", this.postService.doGetPostById(post.getId()));
        map.put("message", "Update Successful");
        return map;
    }

    /**
     * <h2>deletePost</h2>
     * <p>
     * Delete Post
     * </p>
     *
     * @param id int
     *
     * @return MessageResponse
     */
    @DeleteMapping("/{id}")
    public MessageResponse deletePost(@PathVariable int id) {
        this.postService.doDeletePostById(id);
        return new MessageResponse("Deleted Successfully");
    }
}
