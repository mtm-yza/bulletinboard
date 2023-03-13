package com.bulletinBoard.system.bl.service;

import java.util.List;

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.web.form.PostForm;

public interface PostService {
    public void add(PostForm post);
    public void update(PostForm post);
    public void delete(int id);
    public List<PostDTO> getAll();
    public List<PostDTO> getByStatusActive();
    public int getCount();
}
