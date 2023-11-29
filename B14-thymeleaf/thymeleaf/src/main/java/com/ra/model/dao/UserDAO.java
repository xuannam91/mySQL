package com.ra.model.dao;

import com.ra.dto.request.UserLoginDTO;
import com.ra.dto.request.UserRegisterDTO;
import com.ra.model.entity.User;

public interface UserDAO{
    Boolean register(User user);
    User login(UserLoginDTO userLoginDTO);

}
