package com.ra.controller;

import com.ra.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @RequestMapping("")
    public String index(){
        return "account";
    }
    @RequestMapping("/create")
    public String add(Model model){
        Account account = new Account();
        model.addAttribute("account",account);
        return "add";
    }

    @RequestMapping("/store")
    public String store(@ModelAttribute("account") Account account){
        System.out.println("ok");
        System.out.println(account.getUserName());
        System.out.println(account.getPassWord());
        return "home";
    }

}
