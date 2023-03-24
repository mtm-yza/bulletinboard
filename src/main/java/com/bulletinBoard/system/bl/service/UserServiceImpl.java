package com.bulletinBoard.system.bl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.persistance.dao.UserDao;
import com.bulletinBoard.system.persistance.entity.User;
import com.bulletinBoard.system.web.form.UserForm;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean add(UserForm user) {
        userDao.insert(user);
        return true;
    }

    @Override
    public void update(UserForm user, int flag) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserDTO> getAll(int offset, int size) {
        return getUserDto(userDao.getAll(offset, size));
    }

    @Override
    public int getCount() {
        return userDao.getCount();
    }

    private List<UserDTO> getUserDto(List<User> list) {
        return list.stream().map(item -> new UserDTO(item)).collect(Collectors.toList());
    }
}
