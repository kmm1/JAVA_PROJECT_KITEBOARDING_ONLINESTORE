package com.company.controller;

import com.company.entity.Product;
import com.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Kate M on 17.03.2018.
 */

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable("id") Long id) {
        return productService.getByCategoryId(id);
    }

    @GetMapping("/admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsForAdmin() {
        return productService.findAll();
    }
}
