package com.ra.controller;

import com.ra.model.Product;
import com.ra.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    ProductService productService = new ProductService();

    @RequestMapping("")
    public String index(Model model){
    List<Product> list = productService.findAll();
    model.addAttribute("list", list);
    return "product";
    }

    // add
    @RequestMapping("/add-product")
    public String add(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        return "add-product";
    }



    // them moi
    @RequestMapping("/save-product")
    public String save(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:/product";
    }

    // Sửa thông tin
    @RequestMapping("/edit/{id}")
    public String edit (@PathVariable("id") int id, Model model){
        Product product = productService.find(id);
        model.addAttribute("product", product);
        return "edit-product";
    }


    @RequestMapping("/update")
    public String update(@ModelAttribute("product") Product product){
        productService.update(product,product.getProductCode());
        return "redirect:/product";
    }


    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/product";
    }
}
