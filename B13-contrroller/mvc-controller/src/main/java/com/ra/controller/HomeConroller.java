package com.ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "home"})
public class HomeConroller {
@RequestMapping(value = {""})
    public String home(){
        return "homehtml";
    }
}
