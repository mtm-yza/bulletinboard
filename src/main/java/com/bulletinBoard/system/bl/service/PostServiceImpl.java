package com.bulletinBoard.system.bl.service;

import java.util.List;

import com.bulletinBoard.system.bl.dto.Post;

public class PostServiceImpl implements PostService {

    @Override
    public boolean addPost() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Post> getActivePosts() {
        return List.of(new Post(1, "AAA", "AAAAAA", 0), new Post(2, "BBB", "BBBBB", 1));
    }
}
