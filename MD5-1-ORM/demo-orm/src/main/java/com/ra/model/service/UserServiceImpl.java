package com.ra.model.service;

import com.ra.model.entity.User;
import com.ra.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Boolean save(User user) {
        return userRepository.save(user);
    }
    @Override
    public Boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public User find(Integer id) {
        return userRepository.find(id);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }


}
