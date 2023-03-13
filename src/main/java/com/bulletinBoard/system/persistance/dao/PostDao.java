package com.bulletinBoard.system.persistance.dao;

import java.util.List;

import com.bulletinBoard.system.persistance.entity.Post;
import com.bulletinBoard.system.web.form.PostForm;

public interface PostDao {
    public void insert(PostForm post);
    public void update(PostForm post);
    public void delete(int id);
    public List<Post> getAll();
    public List<Post> getByActiveStatus();
    public int getCount();
}
