package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/category")
    public String category(Model model){
        List<Category> list = categoryService.getAll();
        model.addAttribute("list",list);
        return "category/category";
    }

    @GetMapping("/addCategory")
    public String addCategory(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/addCategory";
    }

    @PostMapping("/addCategory")
    public String postAddCategory(@ModelAttribute("category") Category category){
        if(categoryService.create(category)){
            return "redirect:/category";
        }
        return "category/addCategory";
    }


    @GetMapping("/update-category/{id}")
    public String updateCate(@PathVariable("id") int id, Model model){
        Category category = categoryService.find(id);
        model.addAttribute("category", category);
        return "category/editCategory";
    }

    @PostMapping("/update-category")
    public String postUpdateCate(@ModelAttribute("category") Category category){
        if(categoryService.update(category)){
            return "redirect:/category";
        }
        return "redirect:/category";
    }

    @RequestMapping("/delete-category/{id}")
    public String deleteCate(@PathVariable("id") int id){
        categoryService.delete(id);
        return "redirect:/category";
    }

}
