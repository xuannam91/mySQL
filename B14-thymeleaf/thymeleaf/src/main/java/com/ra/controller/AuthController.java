package com.ra.controller;

import com.ra.dto.request.UserLoginDTO;
import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/logon")
    public String logom(Model model){
        UserLoginDTO user = new UserLoginDTO();
        model.addAttribute("user", user);
        return "admin/logon";
    }

    @PostMapping("/logon")
    public String postLogon(@ModelAttribute("user") UserLoginDTO user, HttpSession httpSession){
        ResponseUserLoginDTO userlo = userService.logon(user);
        if(userlo != null){
            httpSession.setAttribute("admin",userlo);
            return "redirect:/admin";
        }
        return "redirect:/logon";
    }



    // cách tắt session
//    @RequestMapping("/logout")
//    public String logout(HttpSession session) {
//        // Xoá session
//        session.invalidate();
//
//        // Chuyển hướng đến trang đăng nhập hoặc trang chính của ứng dụng
//        return "redirect:/login";
//    }
}
