package com.ra.controller;

import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
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
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "product/listproduct";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model){
        Product product = new Product();
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("product",product);
        return "product/add_product";
    }
    @PostMapping("/add-product")
    public String postAddProduct(@ModelAttribute("product") Product product){
        if(productService.saveOrUpdate(product) != null){
            return "redirect:/product";
        }
        return "product/add_product";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model){
        Product product = productService.findById(id);
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("product", product);
        return "product/edit_product";
    }
    @PostMapping("update-product")
    public String postUpdateProduct(@ModelAttribute("product") Product product){
        if(productService.saveOrUpdate(product) != null){
            return "redirect:/product";
        }
        return "product/edit_product";
    }


    @RequestMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product";
    }
}
