package com.bulletinBoard.system.bl.service;

import java.util.List;

import com.bulletinBoard.system.bl.dto.Post;

public interface PostService {
    public boolean addPost();
    public List<Post> getActivePosts();
}
