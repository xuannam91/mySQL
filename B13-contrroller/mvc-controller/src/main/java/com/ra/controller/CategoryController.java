package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public String index(Model model){
       List<Category> categories = categoryService.findAll();
       model.addAttribute("categories",categories);
        return "category/index";
    }

    @RequestMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/add";
    }

    @RequestMapping("/create-category")
    public String create(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        Boolean check = categoryService.create(category);
        if(!check){
            return "category/add?err=";
        }
        redirectAttributes.addFlashAttribute("mess","Thêm mới thành công");
        return "redirect:/category";
    }


    @RequestMapping("/edit-category/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @RequestMapping("/update-category")
    public String update(@ModelAttribute("category") Category category, Model model){
        categoryService.update(category,category.getCategoryId());
        return "redirect:/category";
    }


    @RequestMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") int id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}
