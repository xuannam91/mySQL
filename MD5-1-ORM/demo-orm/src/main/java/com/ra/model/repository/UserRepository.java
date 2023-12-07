package com.ra.model.repository;

import com.ra.model.entity.User;

import java.util.List;

public interface UserRepository {
   List<User> findAll();
   Boolean save(User user);

   Boolean update(User user);
   User find(Integer id);
   void delete(Integer id);
}
