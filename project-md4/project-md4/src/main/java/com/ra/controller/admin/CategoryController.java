package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @RequestMapping("/category")
    public String listcategory(){
        return "admin/category/listcategory";
    }
}
