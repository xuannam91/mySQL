package com.ra.config;

import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.dao.USerDAOimpl;
import com.ra.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        ResponseUserLoginDTO admin = (ResponseUserLoginDTO) httpSession.getAttribute("admin");
        if (admin != null){
            return true;
        }
        response.sendRedirect("/logon");
        return false;
    }
}
