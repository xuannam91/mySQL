package com.example.md5ss03boot.controller;

import com.example.md5ss03boot.entity.Category;
import com.example.md5ss03boot.repository.CategoryRepository;
import com.example.md5ss03boot.service.CategoryService;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("list", categoryService.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "add";
    }

    @PostMapping("/add")
    public String store(@ModelAttribute("category") Category category){
        if(categoryService.saveOrUpdate(category) != null){
            return "redirect:/";
        }
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("category") Category category){
        if(categoryService.saveOrUpdate(category) != null){
            return "redirect:/";
        }
        return "edit";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/";
    }
}
