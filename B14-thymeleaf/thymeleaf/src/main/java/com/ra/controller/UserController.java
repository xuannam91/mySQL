package com.ra.controller;

import com.ra.dto.request.UserLoginDTO;
import com.ra.dto.request.UserRegisterDTO;
import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model){
        UserRegisterDTO user = new UserRegisterDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("user") UserRegisterDTO user,
                               // lưu ý không dùng BindingResult đi cùng với HttpSession vì đụng độ  nhau
                               BindingResult result){
        // khi có lỗi hasErrors trả về true
        if(!result.hasErrors()){
            if(userService.register(user)){
                return "redirect:/login";
            }
        }
        return "register";

    }



    @GetMapping("/login")
    public String login(@CookieValue(required = false, name = "email") String emailCookie ,Model model, HttpServletRequest request){
        UserLoginDTO user = new UserLoginDTO();
        // check cookei có không
        if(emailCookie != null){
            user.setEmail(emailCookie);
            model.addAttribute("checked",true);
        }

        model.addAttribute("user", user);
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") UserLoginDTO user,
                            HttpSession httpSession,
                            RedirectAttributes redirectAttributes,
                            @RequestParam( required = false, name = "checked") Boolean isCheck,
                            HttpServletResponse response, HttpServletRequest request){

        ResponseUserLoginDTO user2 = userService.login(user);
        if(isCheck != null && isCheck){
            // khởi tạo cookie
            Cookie cookieEmail = new Cookie("email",user.getEmail());
            // sét thời gian tồn tại cookei.
            cookieEmail.setMaxAge(24*60*60);
            response.addCookie(cookieEmail);
        }else {
            Cookie[] cookie = request.getCookies();

            if(cookie != null){
                for (int i = 0; i < cookie.length ; i++) {
                    if(cookie[i].getName().equals("email")){
                        cookie[i].setMaxAge(0);
                        // Đặt lại giá trị của cookie trong response để nó được gửi về client và xóa đi
                        response.addCookie(cookie[i]);
                        break;
                    }
                }
            }

        }



        if(user2!=null){
            httpSession.setAttribute("username", user2.getUserName());
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err","Sai tt dang nhap");
        return  "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("username");
        return "redirect:/";
    }


}
