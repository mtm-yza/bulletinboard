package com.bulletinBoard.system.api.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.bulletinBoard.system.api.common.response.MainResponse;
import com.bulletinBoard.system.api.response.PostResponse;
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
    public MainResponse addPost(@Valid @RequestBody PostForm post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new MainResponse("Failed to Add Post");
        }
        post.setId(1);
        this.postService.doAddPost(post);
        return new MainResponse("Saving Successfully");
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
    public MainResponse getPublicPosts(@RequestParam(defaultValue = "0") int page) {
        String email = "admin@gmail.com";
        int count = this.postService.doGetPublicPostCount(email);
        int offset = ControllerUtil.getOffset(page, count);
        List<PostDTO> list = this.postService.doGetPublicPosts(offset, ControllerUtil.PAGE_SIZE, email);
        return new MainResponse(this.getPostResponses(list));
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
    public MainResponse getPostById(@PathVariable int id) {
        PostDTO post = this.postService.doGetPostById(id);
        return new MainResponse(post);
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
    public MainResponse updatePost(@RequestBody PostForm post, BindingResult bindingResult) {
        this.postService.doUpdatePost(post);
        return new MainResponse("Update Successful", new PostResponse(this.postService.doGetPostById(post.getId())));
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
    public MainResponse deletePost(@PathVariable int id) {
        this.postService.doDeletePostById(id);
        return new MainResponse("Deleted Successfully");
    }

    /**
     * <h2>getPostResponses</h2>
     * <p>
     * Convert PostDTOs to PostResponses
     * </p>
     *
     * @param posts List<PostDTO>
     *
     * @return List<PostResponse>
     */
    private List<PostResponse> getPostResponses(List<PostDTO> posts) {
        if (posts.isEmpty())
            return null;
        return posts.stream().map(it -> new PostResponse(it)).collect(Collectors.toList());
    }
}
