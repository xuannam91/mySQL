package com.ra.service;

import com.ra.dto.request.UserLoginDTO;
import com.ra.dto.request.UserRegisterDTO;
import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.entity.User;

public interface UserService {
    Boolean register(UserRegisterDTO user);
    ResponseUserLoginDTO login(UserLoginDTO user);

    ResponseUserLoginDTO logon(UserLoginDTO user);
}
