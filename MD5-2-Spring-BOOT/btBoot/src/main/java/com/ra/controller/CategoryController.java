package com.ra.controller;

import com.ra.entity.Category;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/category")
    public String category(Model model){
        model.addAttribute("list", categoryService.findAll());
        return "category/listcategory";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/add_category";
    }
    @PostMapping("/add-category")
    public String postAddCate(@ModelAttribute("category") Category category){
        if(categoryService.saveOrUpdate(category) != null){
            return "redirect:/category";
        }
        return "category/add_category";
    }

    @GetMapping("/edit-category/{id}")
    public String editCate(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return"category/edit_category";
    }
    @PostMapping("/update-category")
    public String updateCate(@ModelAttribute("category") Category category){
        if(categoryService.saveOrUpdate(category) != null){
            return "redirect:/category";
        }
        return"category/edit_category";
    }

    @RequestMapping("/delete-category/{id}")
    public String deleteCate(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}
