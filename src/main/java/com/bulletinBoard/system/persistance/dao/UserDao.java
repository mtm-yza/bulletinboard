package com.bulletinBoard.system.persistance.dao;

import java.util.List;

import com.bulletinBoard.system.persistance.entity.User;
import com.bulletinBoard.system.web.form.UserForm;

public interface UserDao {
    public void insert(UserForm post);
    public void update(UserForm post);
    public void delete(int id);
    public User getById(int id);
    public User getByEmail(String email);
    public List<User> getAll(int offset, int limit);
    public int getCount();
}
