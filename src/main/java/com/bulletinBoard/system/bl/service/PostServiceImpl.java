package com.bulletinBoard.system.bl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.PostDTO;
import com.bulletinBoard.system.persistance.dao.PostDao;
import com.bulletinBoard.system.persistance.entity.Post;
import com.bulletinBoard.system.web.form.PostForm;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public void add(PostForm post) {
        postDao.insert(post);
    }

    @Override
    public void update(PostForm post) {
        postDao.update(post);
    }

    @Override
    public void delete(int id) {
        postDao.delete(id);
    }

    @Override
    public List<PostDTO> getAll() {
        return getPostDto(postDao.getAll());
    }

    @Override
    public List<PostDTO> getByStatusActive() {
        return getPostDto(postDao.getByActiveStatus());
    }

    @Override
    public int getCount() {
        return postDao.getCount();
    }

    private List<PostDTO> getPostDto(List<Post> postList) {
        return postList.stream()
                .map(item -> new PostDTO(item.getId(), item.getTitle(), item.getDescription(), item.getStatus()))
                .collect(Collectors.toList());
    }
}
