package com.ra.controller;

import com.ra.dto.ProductDTO;
import com.ra.dto.ReponProductDTO;
import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<ReponProductDTO>> getProduct(){
        List<Product> list = productService.findAll();
        List<ReponProductDTO> reponProductDTOList = new ArrayList<>();
        for (Product product : list) {
            ReponProductDTO reponProductDTO = new ReponProductDTO();
            reponProductDTO.setId(product.getId());
            reponProductDTO.setProductName(product.getProductName());
            reponProductDTO.setPrice(product.getPrice());

            if (product.getCategory() != null) {
                reponProductDTO.setCategoryId(product.getCategory().getId());
            }

            reponProductDTOList.add(reponProductDTO);
        }
       return new ResponseEntity<>(reponProductDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReponProductDTO> findById(@PathVariable("id") Integer id){
        Product product = productService.findById(id);
        if(product != null){
            ReponProductDTO reponProductDTO = new ReponProductDTO();
            reponProductDTO.setId(product.getId());
            reponProductDTO.setProductName(product.getProductName());
            reponProductDTO.setPrice(product.getPrice());
            reponProductDTO.setCategoryId(product.getCategory().getId());
            return new ResponseEntity<>(reponProductDTO,HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
        String name = productDTO.getProductName();
        Double price = productDTO.getPrice();
        Integer categoryId = productDTO.getCategoryId();

        Category category = categoryService.findById(categoryId);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Product product = new Product();
        product.setProductName(name);
        product.setPrice(price);
        product.setCategory(category);
        Product product1 = productService.save(product);
        if(product1 == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Integer id){
        Product existingProduct = productService.findById(id);
        if(existingProduct != null){
            Category category = categoryService.findById(productDTO.getCategoryId());
            if(category == null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            existingProduct.setProductName(productDTO.getProductName());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setCategory(category);

           Product product1 = productService.save(existingProduct);
           return new ResponseEntity<>(product1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id){
        if(productService.findById(id) != null){
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
