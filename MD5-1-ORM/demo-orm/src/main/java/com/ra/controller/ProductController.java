package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/product")
    public String product(Model model){
        List<Product> list = productService.getAll();
        model.addAttribute("list",list);
        return "product/product";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("product", product);
        model.addAttribute("listCategory",listCategory);
        return "product/addProduct";
    }
    @PostMapping("/addProduct")
    public String postAddProduct(@ModelAttribute("product") Product product){
        if(productService.create(product)){
            return "redirect:/product";
        }
        return "product/addProduct";
    }


    @GetMapping("/update-product/{id}")
    public String updatePro(@PathVariable("id") int id, Model model){
        Product product = productService.find(id);
        model.addAttribute("product", product);
        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("listCategory",listCategory);
        return "product/editProduct";
    }

    @PostMapping("/update-product")
    public String postUpdatePro(@ModelAttribute("product") Product product){
        if(productService.update(product)){
            return "redirect:/product";
        }
        return "redirect:/product";
    }

    @RequestMapping("/delete-product/{id}")
    public String deletePro(@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/product";
    }

}
