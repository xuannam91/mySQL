package com.ra.model.service;

import com.ra.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    Boolean save(User user);
    Boolean update(User user);
    User find(Integer id);
    void delete(Integer id);
}
