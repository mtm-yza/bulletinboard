package com.bulletinBoard.system.bl.service;

import java.util.List;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.persistance.entity.User;
import com.bulletinBoard.system.web.form.UserForm;

public interface UserService {

    public boolean add(UserForm post);
    public void update(UserForm post, int flag);
    public void delete(int id);
    public User getById(int id);
    public User getByEmail(String email);
    public List<UserDTO> getAll(int offset, int size);
    public int getCount();
}
