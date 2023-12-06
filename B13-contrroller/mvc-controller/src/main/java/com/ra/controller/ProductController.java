package com.ra.controller;

import com.ra.model.dao.ProductDAO;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/product")
    public String index(Model model){
       List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    @RequestMapping("/add-product")
    public String add(Model model){
        List<Category> categoryList = categoryService.findAll();
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categoryList",categoryList);
        return "product/add-product";
    }

    @RequestMapping("/create-product")
    public String create(@ModelAttribute("product") Product product, @RequestParam("img_upload")
    MultipartFile file, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("uploads/images");
        String fileNane = file.getOriginalFilename();
        File destination = new File(path+"/"+fileNane);
        try {
            file.transferTo(destination);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        product.setImage(fileNane);
        productService.create(product);
        return "redirect:/product";
    }


    @RequestMapping("/edit-product/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        List<Category> categoryList = categoryService.findAll();
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categoryList",categoryList);
        return "product/edit-product";
    }


    @RequestMapping("/update-product/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("product") Product product,
                         @RequestParam("img_upload")
                         MultipartFile fileup,
                         HttpServletRequest request) {

        // Lấy thông tin sản phẩm hiện tại từ cơ sở dữ liệu
        Product existingProduct = productService.findById(id);

        // Cập nhật chỉ các trường được nhập từ form
        if (product.getNameProduct() != null) {
            existingProduct.setNameProduct(product.getNameProduct());
        }

        if (product.getPrice() != null) {
            existingProduct.setPrice(Double.valueOf(product.getPrice()));
        }

        if (product.getCategory() != null && product.getCategory().getCategoryId() != null) {
            existingProduct.setCategory(product.getCategory());
        }

        // Kiểm tra và cập nhật ảnh nếu được nhập
        if (fileup != null && !fileup.isEmpty()) {
            String path = request.getServletContext().getRealPath("uploads/images");
            String fileName = fileup.getOriginalFilename();
            File destination = new File(path + "/" + fileName);

            try {
                fileup.transferTo(destination);
                existingProduct.setImage(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Lưu thông tin cập nhật vào cơ sở dữ liệu
        productService.update(existingProduct, existingProduct.getId());

        return "redirect:/product";

    }



    @RequestMapping("/delete-product/{id}")
    public String delete (@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/product";
    }


}
