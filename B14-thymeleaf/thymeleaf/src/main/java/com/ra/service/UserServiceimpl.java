package com.ra.service;

import com.ra.dto.request.UserLoginDTO;
import com.ra.dto.request.UserRegisterDTO;
import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.dao.UserDAO;
import com.ra.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Override
    public Boolean register(UserRegisterDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);
        return userDAO.register(user);
    }

    @Override
    public ResponseUserLoginDTO login(UserLoginDTO user) {
        User user1 = userDAO.login(user);
        ResponseUserLoginDTO responseUserLoginDTO = new ResponseUserLoginDTO();
        responseUserLoginDTO.setId(user1.getId());
        responseUserLoginDTO.setUserName(user1.getUserName());
        responseUserLoginDTO.setEmail(user1.getEmail());
        responseUserLoginDTO.setRole(user1.getRole());
        return responseUserLoginDTO;
    }


    public ResponseUserLoginDTO logon(UserLoginDTO user) {
        ResponseUserLoginDTO userlogon = login(user);
        if(userlogon.getRole() == 1){
            return userlogon;
        }
        return null;
    }



}
