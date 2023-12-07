package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String demo(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("list", list);
        return "home";
    }

    @GetMapping("/addUser")
    public String add(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }
    @PostMapping("/addUser")
    public String postAdd(@ModelAttribute("user") User user){
        if(userService.save(user)){
            return "redirect:/";
        }
        return "add";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        User user = userService.find(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update")
    public String postUpdate(@ModelAttribute("user") User user){
        if(userService.update(user)){
            return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String postUpdate(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/";
    }
}
